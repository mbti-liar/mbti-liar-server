package org.server.mbtiliarserver.question.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class QuestionTest {

    @Test
    void name() {
        Long id = 1L;
        String 질문 = "오늘은 날씨가 좋네요.";

        Question question = new Question(id, 질문);

        Assertions.assertThat(question.getId()).isEqualTo(id);
        Assertions.assertThat(question.getQuestion()).isEqualTo(질문);
    }
}