package org.server.mbtiliarserver.game.application.dto;

public class GameResponse {
    private String sharingCode;

    public GameResponse(String sharingCode) {
        this.sharingCode = sharingCode;
    }

    public String getSharingCode() {
        return sharingCode;
    }
}
