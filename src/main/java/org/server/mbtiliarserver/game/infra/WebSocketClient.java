package org.server.mbtiliarserver.game.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.dto.GameRoomResponse;
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
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/games")
public class WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClient.class);
    private final ObjectMapper objectMapper;
    private final GameService gameService;
    private static Long sequence = 0L;

    private static final Set<GameSession> clients =
        Collections.synchronizedSet(new HashSet<>());

    public WebSocketClient(ObjectMapper objectMapper, GameService gameService) {
        this.objectMapper = objectMapper;
        this.gameService = gameService;
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

        switch (socketMessage.getType()) {
            case CREATE:
                // 방 생성을 요청하면 방을 생성하고, 방 코드를 부여한다.
                GameRoomResponse gameRoomResponse = gameService.create();
                send(session, gameRoomResponse.getSharingCode(), SocketMessageType.CREATE);
                break;
            case ENTRANCE:
                // 입장하면 방 코드 번호와 아이디를 부여받는다.
                gameService.entrance(socketMessage.getSharingCode());
                send(session, socketMessage.getSharingCode(), SocketMessageType.ENTRANCE);
                break;
            case GAME_START:
                // 메시지를 받은 참여자들은 게임을 시작한다.
                // 질문을 응답으로 보낸다.
                // 라이어를 선정해 응답으로 보낸다.
                sendMessage(msg);
                break;
            case REQUEST_VOTE_PROGRESS:
                // 메시지를 받으면 진행 여부 투표를 시작한다.
                sendMessage(msg);
                break;
            case VOTE_PROGRESS:
                // 투표 메시지를 받는다.
                // 투표 메시지를 다 받으면 진행할지 말지를 결정한다.
                sendMessage(msg);
                break;
            case VOTE_LIAR:
                // 투표 메시지를 받는다.
                // 투표 메시지가 방 인원과 맞는 경우 결과를 반환한다.
                // 게임을 종료한다.
                sendMessage(msg);
                break;
        }

    }

    private void send(Session session, String sharingCode, SocketMessageType type) throws IOException {
        sendMessage(objectMapper.writeValueAsString(getResponse(sharingCode, type, session)));
    }

    private SocketMessage getResponse(String socketMessage, SocketMessageType entrance, Session session) {
        return new SocketMessage(socketMessage, entrance, findSession(session).getId());
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
