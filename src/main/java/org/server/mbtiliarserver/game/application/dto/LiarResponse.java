package org.server.mbtiliarserver.game.application.dto;

public class LiarResponse {
    private final Long userId;

    public LiarResponse(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}

