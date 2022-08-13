package org.server.mbtiliarserver.game.application.dto;

public class ProgressResponse {
    private final boolean response;

    public ProgressResponse(boolean response) {
        this.response = response;
    }

    public boolean getResponse() {
        return response;
    }
}
