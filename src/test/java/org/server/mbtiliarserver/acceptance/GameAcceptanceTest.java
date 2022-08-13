package org.server.mbtiliarserver.acceptance;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.server.mbtiliarserver.game.application.dto.PenaltyResponse;
import org.server.mbtiliarserver.game.application.dto.VoteRequest;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class GameAcceptanceTest extends AcceptanceTest {
    String 공유코드;

    @BeforeEach
    public void setUp() {
        게임시작_참여자_네명_입장();
    }

    private void 게임시작_참여자_네명_입장() {
        ExtractableResponse<Response> 방_생성_요청 = 방_생성_요청();
        공유코드 = 방_생성_요청.header("location");

        참여자_입장_요청(공유코드);
        참여자_입장_요청(공유코드);
        참여자_입장_요청(공유코드);
        참여자_입장_요청(공유코드);
    }

    /**
     * Given 사용자가 방을 만든다.
     * When 사람들이 입장하면 사용자의 식별자를 부여받게되고
     * Then 클라이언트에 자신의 정보가 저장된다.
     * Then 사람이 3명 모여 게임을 시작한다.
     */
    @Test
    void start() {
        ExtractableResponse<Response> 방_생성_요청 = 방_생성_요청();

        assertThat(방_생성_요청.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        String sharingCode = 방_생성_요청.header("location");

        ExtractableResponse<Response> 참여자_입장 = 참여자_입장_요청(sharingCode);

        assertThat(참여자_입장.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(참여자_입장.sessionId()).isNotNull();

        ExtractableResponse<Response> 두_번째_참여자_입장 = 참여자_입장_요청(sharingCode);

        assertThat(두_번째_참여자_입장.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 세_번째_참여자_입장 = 참여자_입장_요청(sharingCode);

        assertThat(세_번째_참여자_입장.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 게임_시작_요청 = 게임_시작_요청(sharingCode);

        assertThat(게임_시작_요청.statusCode()).isEqualTo(HttpStatus.OK.value());

    }


    /**
     * Given 게임이 시작하면 MBTI 카드가 설정되고
     * Given 라이어가 설정되며
     * Then 질문이 주어진다.
     */
    @Test
    void start_and_get_question() {
        ExtractableResponse<Response> 네명_게임_시작 = 게임_시작_요청(공유코드);
        assertThat(네명_게임_시작.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(네명_게임_시작.jsonPath().getString("liarResponse")).isNotNull();
        assertThat(네명_게임_시작.jsonPath().getString("mbti")).isNotNull();
    }

    /**
     * Given 질문이 주어지면
     * When 참가자 순서대로 토론을 진행하고
     * Then 게임 진행 여부를 투표한다.
     */
    @Test
    void debate_and_vote() throws JsonProcessingException {
        ExtractableResponse<Response> 네명_게임_시작 = 게임_시작_요청(공유코드);
        assertThat(네명_게임_시작.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 질문_요청 = 질문_요청(공유코드);
        assertThat(질문_요청.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 진행_여부_투표 = 진행_수락(공유코드);
        assertThat(진행_여부_투표.statusCode()).isEqualTo(HttpStatus.OK.value());

        assertThat(진행_여부_투표.jsonPath().getString("response")).isEqualTo("true");
    }


    /**
     * Given 게임을 계속 진행하면
     * When 다시 질문이 주어지고
     * Then 토론을 진행한다.
     */
    @Test
    void progress_and_debate() throws JsonProcessingException {
        ExtractableResponse<Response> 네명_게임_시작 = 게임_시작_요청(공유코드);
        assertThat(네명_게임_시작.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 진행_여부_투표 = 진행_수락(공유코드);
        assertThat(진행_여부_투표.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(진행_여부_투표.jsonPath().getString("response")).isEqualTo("true");

        ExtractableResponse<Response> 질문_요청 = 질문_요청(공유코드);
        assertThat(질문_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 게임을 멈춘다면
     * When 라이어가 누구인지 투표한 후
     * Then 맞으면 라이어에게 벌칙이 주어진다.
     */
    @Test
    void stop_and_vote() throws JsonProcessingException {
        ExtractableResponse<Response> 네명_게임_시작 = 게임_시작_요청(공유코드);
        assertThat(네명_게임_시작.statusCode()).isEqualTo(HttpStatus.OK.value());

        ExtractableResponse<Response> 진행_여부_투표 = 진행_거절(공유코드);
        assertThat(진행_여부_투표.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(진행_여부_투표.jsonPath().getString("response")).isEqualTo("false");

        ExtractableResponse<Response> 라이어_투표 = 라이어_투표(공유코드);
        assertThat(라이어_투표.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(라이어_투표.jsonPath().getList("PenaltiesResponse", PenaltyResponse.class)).hasSize(1);
    }


    private ExtractableResponse<Response> 방_생성_요청() {
        return RestAssured.given().log().all()
            .when().post("/games/rooms")
            .then().log().all().extract();
    }

    private ExtractableResponse<Response> 참여자_입장_요청(String sharingCode) {
        return RestAssured.given().log().all()
            .when().get("/games/rooms/{sharingCode}", sharingCode)
            .then().log().all().extract();
    }

    private ExtractableResponse<Response> 게임_시작_요청(String sharingCode) {
        return RestAssured.given().log().all()
            .when().post("/games/{sharingCode}", sharingCode)
            .then().log().all().extract();
    }

    private ExtractableResponse<Response> 질문_요청(String sharingCode) {
        return RestAssured.given().log().all()
            .when().get("/questions/{sharingCode}", sharingCode)
            .then().log().all().extract();
    }

    private ExtractableResponse<Response> 진행_수락(String sharingCode) throws JsonProcessingException {
        VoteRequest<Boolean> 첫_번째_투표 = new VoteRequest<>(1L, true);
        VoteRequest<Boolean> 두_번쨰_투표 = new VoteRequest<>(2L, true);
        VoteRequest<Boolean> 세_번쨰_투표 = new VoteRequest<>(3L, true);
        VoteRequest<Boolean> 네_번쨰_투표 = new VoteRequest<>(4L, false);
        VotesRequest<Boolean> votesRequest = new VotesRequest<>(Arrays.asList(첫_번째_투표, 두_번쨰_투표, 세_번쨰_투표, 네_번쨰_투표));
        Map<String, String> params = new HashMap<>();
        params.put("votesRequest", objectMapper.writeValueAsString(votesRequest));

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().get("/votes/progress/{sharingCode}", sharingCode)
            .then().log().all()
            .extract();
    }

    private ExtractableResponse<Response> 진행_거절(String sharingCode) throws JsonProcessingException {
        VoteRequest<Boolean> 첫_번째_투표 = new VoteRequest<>(1L, true);
        VoteRequest<Boolean> 두_번쨰_투표 = new VoteRequest<>(2L, false);
        VoteRequest<Boolean> 세_번쨰_투표 = new VoteRequest<>(3L, false);
        VoteRequest<Boolean> 네_번쨰_투표 = new VoteRequest<>(4L, false);
        VotesRequest<Boolean> votesRequest = new VotesRequest<>(Arrays.asList(첫_번째_투표, 두_번쨰_투표, 세_번쨰_투표, 네_번쨰_투표));
        Map<String, String> params = new HashMap<>();
        params.put("votesRequest", objectMapper.writeValueAsString(votesRequest));

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().get("/votes/progress/{sharingCode}", sharingCode)
            .then().log().all()
            .extract();
    }

    private ExtractableResponse<Response> 라이어_투표(String sharingCode) throws JsonProcessingException {
        VoteRequest<Long> 첫_번째_투표 = new VoteRequest<>(1L, 2L);
        VoteRequest<Long> 두_번쨰_투표 = new VoteRequest<>(2L, 2L);
        VoteRequest<Long> 세_번쨰_투표 = new VoteRequest<>(3L, 2L);
        VoteRequest<Long> 네_번쨰_투표 = new VoteRequest<>(4L, 3L);
        VotesRequest<Long> votesRequest = new VotesRequest<>(Arrays.asList(첫_번째_투표, 두_번쨰_투표, 세_번쨰_투표, 네_번쨰_투표));
        Map<String, String> params = new HashMap<>();
        params.put("votesRequest", objectMapper.writeValueAsString(votesRequest));

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when().get("/votes/liar/{sharingCode}", sharingCode)
            .then().log().all()
            .extract();
    }
}
