package org.server.mbtiliarserver.game.application.dto;

public class GameRequest {
    private Long roomId;

    private GameRequest() {/*no-op*/}

    public GameRequest(long roomId) {
        this.roomId = roomId;
    }

    public long getRoomId() {
        return roomId;
    }
}
