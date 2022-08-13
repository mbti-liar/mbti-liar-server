package org.server.mbtiliarserver.game.application.dto;

import java.util.List;

public class VotesRequest<T> {
    private List<VoteRequest<T>> votes;

    private VotesRequest() {/*no-op*/}

    public VotesRequest(List<VoteRequest<T>> votes) {
        this.votes = votes;
    }

    public List<VoteRequest<T>> getVotes() {
        return votes;
    }
}
