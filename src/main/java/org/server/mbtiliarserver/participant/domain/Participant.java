package org.server.mbtiliarserver.participant.domain;

public class Participant {

    Long id;
    String nickname;

    public Participant(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
