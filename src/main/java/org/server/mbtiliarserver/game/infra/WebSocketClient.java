package org.server.mbtiliarserver.game.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.Liar;
import org.server.mbtiliarserver.game.domain.Participant;
import org.server.mbtiliarserver.game.domain.Penalty;
import org.server.mbtiliarserver.question.application.QuestionService;
import org.server.mbtiliarserver.question.domain.Question;
import org.server.mbtiliarserver.question.infra.MemoryQuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@ServerEndpoint(value = "/games")
public class WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClient.class);
    private final static Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GameService gameService = new GameService(new MemoryGameRepository());
    private final QuestionService questionService = new QuestionService(new MemoryQuestionRepository());

    private static long sequence = 0L;

    private synchronized long getSequence() {
        return sequence++;
    }

    private static final Set<GameSession> clients =
        Collections.synchronizedSet(new HashSet<>());


    @OnOpen
    public void onOpen(Session s) {
        log.info("open session : {}", s);
        clients.add(new GameSession(getSequence(), s));
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws Exception {
        log.info("receive session : {}", msg);
        SocketMessage socketMessage = objectMapper.readValue(msg, SocketMessage.class);
        Game game = null;
        if (socketMessage.getSharingCode() != null) {
            game = gameService.findGame(socketMessage.getSharingCode()).orElse(gameService.create(socketMessage.getSharingCode()));
        }

        log.info("game is {}", game);
        switch (socketMessage.getType()) {
            case CREATE:
                // 방 생성을 요청하면 방을 생성하고, 방 코드를 부여한다.
                game = gameService.create(socketMessage.getSharingCode());
                requireNonNull(game).getParticipants().add(new Participant(findSession(session).getId(), socketMessage.getMessage()));
                send(session, game.getSharingCode(), SocketMessageType.CREATE, null);
                break;
            case ENTRANCE:
                // 입장하면 방 코드 번호와 아이디를 부여받는다.
                requireNonNull(game).getParticipants().add(new Participant(findSession(session).getId(), socketMessage.getMessage()));
                send(session, socketMessage.getSharingCode(), SocketMessageType.ENTRANCE, null);
                break;
            case GAME_START:
                // 메시지를 받은 참여자들은 게임을 시작한다.
                // 질문을 응답으로 보낸다.
                List<Question> questions = questionService.getQuestions();
                Game finalGame = game;
                List<Question> collect = questions.stream().filter(question -> !finalGame.getCompletedQuestions().contains(question)).collect(Collectors.toList());
                int questionIdx = random.nextInt(collect.size());
                Question question = collect.get(questionIdx);

                game.getCompletedQuestions().add(question);

                // 라이어를 선정해 응답으로 보낸다.
                Map<String, Object> params = new HashMap<>();
                Liar liar;
                if (game.getLiarId() == null) {
                    liar = gameService.selectLiar(socketMessage.getSharingCode());
                    game.setLiar(liar.getId());
                } else {
                    liar = new Liar(game.getLiarId());
                }

                params.put("questionResponse", question.getQuestion());
                params.put("liarResponse", liar.getId());

                send(session, socketMessage.getSharingCode(), SocketMessageType.GAME_START, objectMapper.writeValueAsString(params));
                break;
            case REQUEST_VOTE_PROGRESS:
                requireNonNull(game).addCount();
                if (game.getRequestCount() == game.getParticipants().size()) {
                    send(session, socketMessage.getSharingCode(), SocketMessageType.REQUEST_VOTE_PROGRESS, null);
                }
                break;
            case VOTE_PROGRESS:
                // 투표 메시지를 받는다.
                requireNonNull(game).getVotes().add(objectMapper.readValue(socketMessage.getMessage(), Boolean.class));
                if (game.getVotes().size() == game.getParticipants().size()) {
                    // 투표 메시지를 다 받으면 진행할지 말지 결정합니다.
                    int trueSize = (int) game.getVotes().stream().filter(aBoolean -> aBoolean.equals(true)).count();
                    if (game.getParticipants().size() / 2 < trueSize) {
                        send(session, socketMessage.getSharingCode(), SocketMessageType.GAME_START, null);
                    } else {
                        send(session, socketMessage.getSharingCode(), SocketMessageType.VOTE_LIAR, null);
                    }
                }
                break;
            case VOTE_LIAR:
                // 투표 메시지를 받는다.
                requireNonNull(game).getSelectedParticipants().add(objectMapper.readValue(socketMessage.getMessage(), Long.class));
                if (game.getSelectedParticipants().size() == game.getParticipants().size()) {
                    // 투표 메시지가 방 인원과 맞는 경우 결과를 반환한다.
                    Map<Long, Integer> result = new HashMap<>();
                    game.getSelectedParticipants().forEach(
                        aLong -> {
                            if (result.containsKey(aLong)) {
                                result.put(aLong, 0);
                            } else {
                                result.put(aLong, result.get(aLong) + 1);
                            }
                        }
                    );
                    long selectedUser = 0L;
                    int vote = 0;
                    for (Map.Entry<Long, Integer> entry : result.entrySet()) {
                        if (vote < entry.getValue()) {
                            selectedUser = entry.getKey();
                            vote = entry.getValue();
                        }
                    }
                    Penalty penalty = gameService.getPenalty();

                    Map<String, Object> resultParams = new HashMap<>();
                    resultParams.put("selectedUser", selectedUser);
                    resultParams.put("penalty", penalty.getPenalty());
                    // 게임을 종료한다.
                    gameService.delete(socketMessage.getSharingCode());
                    send(session, socketMessage.getSharingCode(), SocketMessageType.END, objectMapper.writeValueAsString(resultParams));

                }
                break;
        }

    }

    private GameSession findSession(Session session) {
        return clients.stream().filter(gameSession -> gameSession.getSession().equals(session)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private void send(Session session, String sharingCode, SocketMessageType type, String message) throws IOException {
        SocketMessage socketMessage = new SocketMessage(sharingCode, type, findSession(session).getId(), message);
        for (GameSession s : clients) {
            log.info("send session : {}", socketMessage);
            s.getSession().getBasicRemote().sendText(objectMapper.writeValueAsString(socketMessage));
        }
    }


    @OnClose
    public void onClose(Session s) {
        log.info("close session : {}", s);
        clients.remove(findSession(s));
    }

}
