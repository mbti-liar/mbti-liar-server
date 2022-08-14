package org.server.mbtiliarserver.game.infra;

public class SocketMessage {
    private String sharingCode;
    private SocketMessageType type;
    private Long userId;
    private String message;

    public SocketMessage() {
    }

    public SocketMessage(String sharingCode, SocketMessageType type, Long userId, String message) {
        this.sharingCode = sharingCode;
        this.type = type;
        this.userId = userId;
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
