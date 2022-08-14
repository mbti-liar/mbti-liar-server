package org.server.mbtiliarserver.game.domain;

import java.util.Objects;

public class Participant {

    private Long id;
    private String nickname;

    public Participant(Long id, String nickname) {
        if(nickname == null || nickname.isBlank()){
            throw new IllegalArgumentException();
        }
        this.id = id;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Participant{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            '}';
    }
}
