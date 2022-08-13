package org.server.mbtiliarserver.gameroom.application.dto;

public class GameRoomResponse {
    private String sharingCode;

    public GameRoomResponse(String sharingCode) {
        this.sharingCode = sharingCode;
    }

    public String getSharingCode() {
        return sharingCode;
    }
}
