package org.server.mbtiliarserver.game.domain;

public enum Judgment {
    JUDGING("J"),
    PERCEIVING("P");

    private String type;

    Judgment(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
