package org.server.mbtiliarserver.game.application.dto;

import java.util.List;

public class ParticipantsResponse {
    private List<ParticipantResponse> participantsResponse;

    public ParticipantsResponse(List<ParticipantResponse> participantsResponse) {
        this.participantsResponse = participantsResponse;
    }

    public List<ParticipantResponse> getParticipantResponses() {
        return participantsResponse;
    }
}
