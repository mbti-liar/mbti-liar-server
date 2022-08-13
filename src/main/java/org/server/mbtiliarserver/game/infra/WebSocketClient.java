package org.server.mbtiliarserver.game.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.LiarService;
import org.server.mbtiliarserver.game.application.VoterService;
import org.server.mbtiliarserver.game.application.dto.GameRoomResponse;
import org.server.mbtiliarserver.game.application.dto.LiarResponse;
import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.Participant;
import org.server.mbtiliarserver.question.application.QuestionService;
import org.server.mbtiliarserver.question.application.dto.QuestionResponse;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@ServerEndpoint(value = "/games")
public class WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClient.class);
    private final ObjectMapper objectMapper;
    private final GameService gameService;
    private final QuestionService questionService;
    private final VoterService voterService;
    private final LiarService liarService;
    private static Long sequence = 0L;

    private static final Set<GameSession> clients =
        Collections.synchronizedSet(new HashSet<>());

    public WebSocketClient(ObjectMapper objectMapper, GameService gameService, QuestionService questionService, VoterService voterService, LiarService liarService) {
        this.objectMapper = objectMapper;
        this.gameService = gameService;
        this.questionService = questionService;
        this.voterService = voterService;
        this.liarService = liarService;
    }


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
            game = gameService.findGame(socketMessage.getSharingCode());
        }

        switch (socketMessage.getType()) {
            case CREATE:
                // 방 생성을 요청하면 방을 생성하고, 방 코드를 부여한다.
                game = gameService.create();
                requireNonNull(game).getParticipants().add(new Participant(findSession(session).getId(), socketMessage.getMessage()));
                send(session, game.getSharingCode(), SocketMessageType.CREATE, null);
                break;
            case ENTRANCE:
                // 입장하면 방 코드 번호와 아이디를 부여받는다.
                requireNonNull(game).getParticipants().add(new Participant(findSession(session).getId(), socketMessage.getMessage()));
                gameService.entrance(socketMessage.getSharingCode());
                send(session, socketMessage.getSharingCode(), SocketMessageType.ENTRANCE, null);
                break;
            case GAME_START:
                // 메시지를 받은 참여자들은 게임을 시작한다.
                // 질문을 응답으로 보낸다.
                QuestionResponse questionResponse = questionService.getQuestion(socketMessage.getSharingCode());
                // 라이어를 선정해 응답으로 보낸다.
                LiarResponse liarResponse = liarService.selectLiar(socketMessage.getSharingCode());

                Map<String, Object> params = new HashMap<>();
                params.put("questionResponse", questionResponse);
                params.put("liarResponse", liarResponse);
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
                    boolean isProgress = game.getParticipants().size() / 2 < trueSize;
                    send(session, socketMessage.getSharingCode(), SocketMessageType.VOTE_PROGRESS, objectMapper.writeValueAsString(isProgress));
                }
                break;
            case VOTE_LIAR:
                // 투표 메시지를 받는다.
                requireNonNull(game).getSelectedParticipants().add(objectMapper.writeValueAsString(socketMessage.getMessage(), Long.getLong()));
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

                    // 게임을 종료한다.
                    send(session, socketMessage.getSharingCode(), SocketMessageType.VOTE_LIAR, null);
                }
                break;
        }

    }

    private void send(Session session, String sharingCode, SocketMessageType type, String message) throws IOException {
        sendMessage(objectMapper.writeValueAsString(getResponse(sharingCode, type, session, message)));
    }

    private SocketMessage getResponse(String socketMessage, SocketMessageType entrance, Session session, String message) {
        return new SocketMessage(socketMessage, entrance, findSession(session).getId(), message);
    }

    private void sendMessage(String msg) throws IOException {
        for (GameSession s : clients) {
            log.info("send session : {}", msg);
            s.getSession().getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(Session s) {
        log.info("close session : {}", s);
        GameSession session = findSession(s);
        clients.remove(session);
    }

    private GameSession findSession(Session s) {
        return clients.stream()
            .filter(gameSession -> gameSession.getSession().equals(s))
            .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private synchronized Long getSequence() {
        return sequence++;
    }
}
