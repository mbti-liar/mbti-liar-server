package org.server.mbtiliarserver.question.domain;

import java.util.Optional;

public interface QuestionRepository {
    Question save(Question question);

    Optional<Question> findById(long id);
}
