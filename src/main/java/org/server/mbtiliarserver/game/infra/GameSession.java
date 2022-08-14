package org.server.mbtiliarserver.game.infra;


import javax.websocket.Session;
import java.util.Objects;

public class GameSession {
    private long id;
    private String nickname;
    private Session session;

    public GameSession(long id, String nickname, Session session) {
        this.id = id;
        this.nickname = nickname;
        this.session = session;
    }

    public long getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(getSession(), that.getSession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSession());
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
