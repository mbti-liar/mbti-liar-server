package org.server.mbtiliarserver.game.infra;

public class SocketMessage {
    private String sharingCode;
    private SocketMessageType type;
    private Long userId;

    public SocketMessage(String sharingCode, SocketMessageType type, Long userId) {
        this.sharingCode = sharingCode;
        this.type = type;
        this.userId = userId;
    }

    public String getSharingCode() {
        return sharingCode;
    }

    public SocketMessageType getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }
}
