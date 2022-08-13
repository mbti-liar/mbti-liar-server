package org.server.mbtiliarserver.documentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.VoterService;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.server.mbtiliarserver.game.application.dto.LiarResponse;
import org.server.mbtiliarserver.game.application.dto.MbtiResponse;
import org.server.mbtiliarserver.game.application.dto.ParticipantResponse;
import org.server.mbtiliarserver.game.application.dto.ParticipantsResponse;
import org.server.mbtiliarserver.game.application.dto.PenaltiesResponse;
import org.server.mbtiliarserver.game.application.dto.PenaltyResponse;
import org.server.mbtiliarserver.game.application.dto.ProgressResponse;
import org.server.mbtiliarserver.game.application.dto.VoteRequest;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.server.mbtiliarserver.game.domain.Decision;
import org.server.mbtiliarserver.game.domain.Judgment;
import org.server.mbtiliarserver.game.domain.Personality;
import org.server.mbtiliarserver.game.domain.Sense;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class GameDocumentation extends Documentation {

    @MockBean
    private GameService gameService;

    @MockBean
    private VoterService voterService;


    @Test
    void start() {
        long liarId = 1L;
        LiarResponse liarResponse = new LiarResponse(liarId);
        MbtiResponse mbtiResponse = new MbtiResponse(Personality.EXTROVERSION, Sense.SENSING, Decision.FEELING, Judgment.JUDGING);

        ParticipantsResponse participantsResponse = new ParticipantsResponse(Arrays.asList(new ParticipantResponse(3L), new ParticipantResponse(2L)));
        GameResponse gameResponse = new GameResponse(liarResponse, mbtiResponse, participantsResponse);
        when(gameService.start(any())).thenReturn(gameResponse);

        Map<String, String> params = new HashMap<>();
        params.put("roomId", "2");

        given()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .filter(
                document("game/start",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()))
            ).when()
            .post("games")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void voteLiar() throws JsonProcessingException {
        String sharingCode = "ABD23K";
        VoteRequest<Long> 첫_번째_투표 = new VoteRequest<>(1L, 2L);
        VoteRequest<Long> 두_번쨰_투표 = new VoteRequest<>(2L, 2L);
        VoteRequest<Long> 세_번쨰_투표 = new VoteRequest<>(3L, 1L);
        VotesRequest<Long> votesRequest = new VotesRequest<>(Arrays.asList(첫_번째_투표, 두_번쨰_투표, 세_번쨰_투표));

        PenaltyResponse 벌칙받는_사람 = new PenaltyResponse(2L, "술 마시기");
        PenaltiesResponse value = new PenaltiesResponse(List.of(벌칙받는_사람));
        when(voterService.voteLiar(any(), sharingCode)).thenReturn(value);

        Map<String, String> params = new HashMap<>();
        params.put("votesRequest", objectMapper.writeValueAsString(votesRequest));

        given()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .filter(
                document("vote/liar",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()))
            ).when().post("votes/liar")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void voteProgress() throws JsonProcessingException {
        String sharingCode = "ABD23K";
        ProgressResponse value = new ProgressResponse(true);
        when(voterService.voteProgress(any(), sharingCode)).thenReturn(value);

        VoteRequest<Boolean> 첫_번째_투표 = new VoteRequest<>(1L, true);
        VoteRequest<Boolean> 두_번쨰_투표 = new VoteRequest<>(2L, false);
        VoteRequest<Boolean> 세_번쨰_투표 = new VoteRequest<>(3L, false);
        VotesRequest<Boolean> votesRequest = new VotesRequest<>(Arrays.asList(첫_번째_투표, 두_번쨰_투표));

        Map<String, String> params = new HashMap<>();
        params.put("votesRequest", objectMapper.writeValueAsString(votesRequest));

        given()
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .filter(
                document("vote/progress",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()))
            ).when().post("votes/progress")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}
