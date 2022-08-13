package org.server.mbtiliarserver.question.application.dto;

public class QuestionResponse {
    private String question;

    public QuestionResponse(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
