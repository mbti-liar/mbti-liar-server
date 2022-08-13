package org.server.mbtiliarserver.game.domain;

public enum Decision {
    THINKING("T"),
    FEELING("F");

    private String type;

    Decision(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
