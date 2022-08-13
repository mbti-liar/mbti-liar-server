package org.server.mbtiliarserver.game.domain;

public class Participant {

    Long id;
    String participant;

    public Participant(Long id, String participant) {
        this.id = id;
        this.participant = participant;
    }

    public Long getId() {
        return id;
    }

    public String getParticipant() {
        return participant;
    }
}
