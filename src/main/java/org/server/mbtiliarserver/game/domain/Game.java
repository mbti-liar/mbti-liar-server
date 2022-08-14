package org.server.mbtiliarserver.game.domain;

import org.server.mbtiliarserver.game.exception.mbtiCardNotNullAndBlankException;
import org.server.mbtiliarserver.question.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String mbtiCard;
    private Long liarId;
    private boolean gameProgress;
    private List<Participant> participants = new ArrayList<>();
    private List<Question> completedQuestions = new ArrayList<>();
    private Long requestCount;
    private List<Boolean> votes = new ArrayList<>();
    private List<Long> selectedParticipants = new ArrayList<>();
    private String sharingCode;

    public Game(String mbtiCard, Long liarId, boolean gameProgress, Long requestCount, String sharingCode) {
        if (mbtiCard == null || mbtiCard.isBlank()) {
            throw new mbtiCardNotNullAndBlankException("mbtiCard는 비어 있을 수 없습니다.");
        }

        this.mbtiCard = mbtiCard;
        this.liarId = liarId;
        this.gameProgress = gameProgress;
        this.requestCount = requestCount;
        this.sharingCode = sharingCode;
    }

    public Game(String sharingCode) {
        this.sharingCode = sharingCode;
    }

    public String getMbtiCard() {
        return mbtiCard;
    }

    public Long getLiarId() {
        return liarId;
    }

    public boolean isGameProgress() {
        return gameProgress;
    }

    public List<Question> getCompletedQuestions() {
        return completedQuestions;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public List<Boolean> getVotes() {
        return votes;
    }

    public List<Long> getSelectedParticipants() {
        return selectedParticipants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void addCount() {
        this.requestCount += 1L;
    }

    public String getSharingCode() {
        return sharingCode;
    }

    public void setLiar(Long liarId) {
        this.liarId = liarId;
    }

    @Override
    public String toString() {
        return "Game{" +
            "mbtiCard='" + mbtiCard + '\'' +
            ", liarId=" + liarId +
            ", gameProgress=" + gameProgress +
            ", participants=" + participants +
            ", completedQuestions=" + completedQuestions +
            ", requestCount=" + requestCount +
            ", votes=" + votes +
            ", selectedParticipants=" + selectedParticipants +
            ", sharingCode='" + sharingCode + '\'' +
            '}';
    }
}
