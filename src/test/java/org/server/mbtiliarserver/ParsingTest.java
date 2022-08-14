package org.server.mbtiliarserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.server.mbtiliarserver.game.infra.SocketMessage;
import org.server.mbtiliarserver.game.infra.SocketMessageType;

import java.util.HashMap;
import java.util.Map;

public class ParsingTest {
    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Test
//    void create() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("SSK23L", SocketMessageType.CREATE, null, "nickname", nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//        SocketMessage response = new SocketMessage("SSK23L", SocketMessageType.CREATE, 2L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//    @Test
//    void entrance() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.ENTRANCE, null, "nickname", nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.ENTRANCE, 3L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//    @Test
//    void start() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.GAME_START, 2L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("questionResponse", "오늘 너무 슬퍼요 ㅜ");
//        params.put("liarResponse", "3L");
//
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.ENTRANCE, 2L, objectMapper.writeValueAsString(params), nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//    @Test
//    void requestVote() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.REQUEST_VOTE_PROGRESS, 3L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.REQUEST_VOTE_PROGRESS, 3L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//    @Test
//    void voteProgress1() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.VOTE_PROGRESS, 3L, "true", nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.GAME_START, 3L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//    @Test
//    void voteProgress2() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.VOTE_PROGRESS, 3L, "true", nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.VOTE_LIAR, 3L, null, nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
//
//
//    @Test
//    void voteLiar() throws JsonProcessingException {
//        SocketMessage request = new SocketMessage("DDSW12", SocketMessageType.ENTRANCE, 2L, "3", nickname);
//        System.out.println(objectMapper.writeValueAsString(request));
//
//        Map<String, Object> resultParams = new HashMap<>();
//        resultParams.put("selectedUser", "2");
//        resultParams.put("penalty", "옆 사람이 벌칙 정해주기");
//
//        SocketMessage response = new SocketMessage("DDSW12", SocketMessageType.ENTRANCE, 3L, objectMapper.writeValueAsString(resultParams), nickname);
//        System.out.println(objectMapper.writeValueAsString(response));
//    }
}
