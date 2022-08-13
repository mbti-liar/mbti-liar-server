package org.server.mbtiliarserver.game.application.dto;

public class GameResponse {
    private final LiarResponse liarResponse;
    private final MbtiResponse mbtiResponse;

    public GameResponse(LiarResponse liarResponse, MbtiResponse mbtiResponse) {
        this.liarResponse = liarResponse;
        this.mbtiResponse = mbtiResponse;
    }

    public LiarResponse getLiarResponse() {
        return liarResponse;
    }

    public MbtiResponse getMbtiResponse() {
        return mbtiResponse;
    }
}
