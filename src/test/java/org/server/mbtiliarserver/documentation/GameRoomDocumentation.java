package org.server.mbtiliarserver.documentation;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class GameRoomDocumentation extends Documentation {

    @MockBean
    private GameService gameService;

    @Test
    void create() {
        when(gameService.create()).thenReturn(new GameResponse("K12JQx"));

        given()
            .filter(document("game/create",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint())))
            .when().post("/games")
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value()).
            extract();
    }



    @Test
    void entrance() {
        doNothing().when(gameService).entrance(any());

        given()
            .filter(document("game/entrance",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/games/{sharingCode}", "K12JQx")
            .then().log().all()
            .statusCode(HttpStatus.OK.value()).
            extract();
    }

    @Test
    void end() {
        doNothing().when(gameService).delete();

        given()
            .filter(document("game/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().delete("/games")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}
