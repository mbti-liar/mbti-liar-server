package org.server.mbtiliarserver.question.ui;

import org.server.mbtiliarserver.question.application.QuestionService;
import org.server.mbtiliarserver.question.application.dto.QuestionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<QuestionResponse> getQuestion() {
        QuestionResponse questionResponse = questionService.getQuestion();
        return ResponseEntity.ok(questionResponse);
    }

}
