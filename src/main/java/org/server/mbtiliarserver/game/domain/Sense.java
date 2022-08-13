package org.server.mbtiliarserver.game.domain;

public enum Sense {
    SENSING("S"),
    INTUITION("N");

    private String type;

    Sense(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
