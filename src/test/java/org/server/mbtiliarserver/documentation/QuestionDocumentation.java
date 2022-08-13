package org.server.mbtiliarserver.documentation;

import org.junit.jupiter.api.Test;
import org.server.mbtiliarserver.question.application.QuestionService;
import org.server.mbtiliarserver.question.application.dto.QuestionResponse;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;


public class QuestionDocumentation extends Documentation {

    @MockBean
    QuestionService questionService;

    @Test
    void getQuestion() {
        String sharingCode = "AS23KS";
        when(questionService.getQuestion(sharingCode)).thenReturn(new QuestionResponse("오늘따라 기분이 좋아"));

        given()
            .filter(document("questions",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .when().get("/questions")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }
}
