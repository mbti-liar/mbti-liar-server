package org.server.mbtiliarserver.game.application.dto;

public class VoteRequest<T> {

    private Long userId;
    private T vote;

    private VoteRequest() {/*no-op*/}

    public VoteRequest(long userId, T vote) {
        this.userId = userId;
        this.vote = vote;
    }

    public Long getUserId() {
        return userId;
    }

    public T getVote() {
        return vote;
    }
}
