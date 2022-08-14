package org.server.mbtiliarserver.game.domain;

import org.server.mbtiliarserver.game.exception.mbtiCardNotNullAndBlankException;
import org.server.mbtiliarserver.question.domain.Question;

import java.util.List;

public class Game {

    private String mbtiCard;
    private Long liarId;
    private boolean gameProgress;
    private List<Participant> participants;
    private List<Question> completedQuestions;
    private Long requestCount;
    private List<Boolean> votes;
    private List<Long> selectedParticipants;
    private String sharingCode;

    public Game(String mbtiCard, Long liarId, boolean gameProgress, List<Participant> participants, List<Question> completedQuestions, Long requestCount, List<Boolean> votes, List<Long> selectedParticipants, String sharingCode) {
        if(mbtiCard == null || mbtiCard.isBlank()){
            throw new mbtiCardNotNullAndBlankException("mbtiCard는 비어 있을 수 없습니다.");
        }

        this.mbtiCard = mbtiCard;
        this.liarId = liarId;
        this.gameProgress = gameProgress;
        this.participants = participants;
        this.completedQuestions = completedQuestions;
        this.requestCount = requestCount;
        this.votes = votes;
        this.selectedParticipants = selectedParticipants;
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
}
