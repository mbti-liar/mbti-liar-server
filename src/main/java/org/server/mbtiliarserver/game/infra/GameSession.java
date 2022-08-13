package org.server.mbtiliarserver.game.infra;


import javax.websocket.Session;
import java.util.Objects;

public class GameSession {
    private long id;
    private Session session;

    public GameSession(long id, Session session) {
        this.id = id;
        this.session = session;
    }

    public long getId() {
        return id;
    }

    public Session getSession() {
        return session;
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
}
