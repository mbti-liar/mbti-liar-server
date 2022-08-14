package org.server.mbtiliarserver.game.infra;

public class SocketMessage {
    private String sharingCode;
    private SocketMessageType type;
    private Long userId;
    private String message;

    private String nickname;

    public SocketMessage() {
    }

    public SocketMessage(String sharingCode, SocketMessageType type, Long userId, String message, String nickname) {
        this.sharingCode = sharingCode;
        this.type = type;
        this.userId = userId;
        this.message = message;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }
}
