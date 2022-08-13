package org.server.mbtiliarserver.question.infra;

import org.server.mbtiliarserver.question.domain.Question;
import org.server.mbtiliarserver.question.domain.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryQuestionRepository implements QuestionRepository {

    private final Map<Long, Question> questionMap = new HashMap<>();

    private static Long sequence = 0L;

    @Override
    public Question save(Question question) {
        Long id = sequence++;
        return questionMap.put(id, new Question(id, question.getQuestion()));
    }

    @Override
    public Optional<Question> findById(long id) {
        return Optional.of(questionMap.get(id));
    }
}
