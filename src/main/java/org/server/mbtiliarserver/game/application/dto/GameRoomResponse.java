package org.server.mbtiliarserver.game.application.dto;

public class GameRoomResponse {
    private String sharingCode;

    public GameRoomResponse(String sharingCode) {
        this.sharingCode = sharingCode;
    }

    public String getSharingCode() {
        return sharingCode;
    }
}
