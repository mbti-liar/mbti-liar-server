package org.server.mbtiliarserver.question.domain;

public class Question {

    private Long id;
    private String question;

    public Question(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
