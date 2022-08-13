package org.server.mbtiliarserver.question.application;

import org.server.mbtiliarserver.question.application.dto.QuestionResponse;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    public QuestionResponse getQuestion(String sharingCode) {
        // 게임에서 진행한 질문 목록을 가져온다.

        // 전체 질문 목록에서 진행한 목록을 제외한다.
        // 그 중에 하나를 임의로 가져온다.
        return null;
    }
}
