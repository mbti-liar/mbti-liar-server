package org.server.mbtiliarserver;

import org.server.mbtiliarserver.question.domain.Question;
import org.server.mbtiliarserver.question.domain.QuestionRepository;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Arrays;

public class CustomInitBinder {

    private final QuestionRepository questionRepository;

    public CustomInitBinder(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    private final String[] questions = {
        "피곤해서 드라이 샴푸로 머리감았어.",
        "차사고 났어.",
        "힘들게 돈모아서 아이패드 샀어.",
        "나 아파.",
        "노래를 들을 때 멜로디가 좋으면?",
        "여행 가기 전날 하는 생각은?",
        "멍 때릴 때 하는 상상은?",
        "아무 생각하지 마",
        "주중에 회사에서 일한 당신 주말에는 무엇을 할 것인가요?",
        " 약속을 앞두고 준비를 어떻게 하나요?"};

    @InitBinder
    public void loadData() {
        Arrays.stream(questions)
            .map(s -> new Question(null, s))
            .forEach(questionRepository::save);
    }
}
