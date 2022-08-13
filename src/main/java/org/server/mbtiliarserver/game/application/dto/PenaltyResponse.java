package org.server.mbtiliarserver.game.application.dto;

public class PenaltyResponse {

    private final Long userId;
    private final String penalty;

    public PenaltyResponse(Long userId, String penalty) {
        this.userId = userId;
        this.penalty = penalty;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPenaltyResponse() {
        return penalty;
    }
}
