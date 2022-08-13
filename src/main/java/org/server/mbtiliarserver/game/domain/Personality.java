package org.server.mbtiliarserver.game.domain;

public enum Personality {
    EXTROVERSION("E"),
    INTROVERSION("I");

    private String type;

    Personality(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
