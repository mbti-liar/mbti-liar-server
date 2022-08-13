package org.server.mbtiliarserver.question.application;

import org.server.mbtiliarserver.question.domain.Question;
import org.server.mbtiliarserver.question.domain.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.getQuestions();
    }
}
