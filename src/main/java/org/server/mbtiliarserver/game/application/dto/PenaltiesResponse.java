package org.server.mbtiliarserver.game.application.dto;

import java.util.List;

public class PenaltiesResponse {
    private final List<PenaltyResponse> penaltyResponses;

    public PenaltiesResponse(List<PenaltyResponse> penaltyResponses) {
        this.penaltyResponses = penaltyResponses;
    }

    public List<PenaltyResponse> getVoteResult() {
        return penaltyResponses;
    }
}
