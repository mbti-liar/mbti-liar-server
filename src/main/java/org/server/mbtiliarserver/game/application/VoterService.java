package org.server.mbtiliarserver.game.application;

import org.server.mbtiliarserver.game.application.dto.PenaltiesResponse;
import org.server.mbtiliarserver.game.application.dto.ProgressResponse;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.springframework.stereotype.Service;

@Service
public class VoterService {
    public PenaltiesResponse voteLiar(VotesRequest<Long> voteRequest, String sharingCode) {
        return null;
    }

    public ProgressResponse voteProgress(VotesRequest<Boolean> voteRequest, String sharingCode) {
        return null;
    }

    public Long request(String sharingCode, Long id) {
        return null;
    }

    public Long voteProgress(String sharingCode, String message, Long id) {
        return null;
    }

    public Long voteLiar(String sharingCode, String message, Long id) {
        return null;
    }
}
