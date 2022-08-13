package org.server.mbtiliarserver.game.application.dto;

public class ParticipantResponse {

    private final Long participantId;

    public ParticipantResponse(Long participantId) {
        this.participantId = participantId;
    }

    public Long getParticipantId() {
        return participantId;
    }
}
