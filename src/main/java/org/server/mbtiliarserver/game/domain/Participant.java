package org.server.mbtiliarserver.game.domain;

public class Participant {

    Long id;
    String nickName;

    public Participant(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }
}
