package org.server.mbtiliarserver.game.application;

import org.server.mbtiliarserver.game.application.dto.GameRequest;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.server.mbtiliarserver.game.application.dto.PenaltiesResponse;
import org.server.mbtiliarserver.game.application.dto.ProgressResponse;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    public GameResponse start(GameRequest gameRequest) {
        return null;
    }

    public PenaltiesResponse voteLiar(VotesRequest<Long> voteRequest) {
        return null;
    }

    public ProgressResponse voteProgress(VotesRequest<Boolean> voteRequest) {
        return null;
    }
}
