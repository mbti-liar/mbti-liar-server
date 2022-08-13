package org.server.mbtiliarserver.game.application.dto;

import java.util.List;

public class ParticipantsResponse {
    private List<ParticipantResponse> participantResponses;

    public ParticipantsResponse(List<ParticipantResponse> participantResponses) {
        this.participantResponses = participantResponses;
    }

    public List<ParticipantResponse> getParticipantResponses() {
        return participantResponses;
    }
}
