package org.server.mbtiliarserver.question.domain;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Question save(Question question);

    Optional<Question> findById(long id);

    List<Question> getQuestions();
}
