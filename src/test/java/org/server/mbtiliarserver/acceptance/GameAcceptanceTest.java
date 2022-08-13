package org.server.mbtiliarserver.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class GameAcceptanceTest extends AcceptanceTest {

    /**
     * Given 사용자가 방을 만들면
     * When 사람들이 입장하고
     * Then 사람이 3명 모여 게임을 시작한다.
     */
    @Test
    void start() {
 
    }

    /**
     * Given 게임이 시작하면 MBTI 카드가 설정되고
     * Given 라이어가 설정되며
     * Then 질문이 주어진다.
     */
    @Test
    void start_and_get_question() {
    }

    /**
     * Given 질문이 주어지면
     * When 참가자 순서대로 토론을 진행하고
     * Then 게임 진행 여부를 투표한다.
     */
    @Test
    void debate_and_vote() {
    }

    /**
     * Given 게임을 계속 진행하면
     * When 다시 질문이 주어지고
     * Then 토론을 진행한다.
     */
    @Test
    void progress_and_debate() {
    }

    /**
     * Given 게임을 멈춘다면
     * When 라이어가 누구인지 투표한 후
     * Then 맞으면 라이어에게 벌칙이 주어진다.
     */
    @Test
    void stop_and_vote() {
    }
}
