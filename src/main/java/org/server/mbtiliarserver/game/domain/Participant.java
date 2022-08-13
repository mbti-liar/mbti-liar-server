package org.server.mbtiliarserver.game.domain;

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
}
