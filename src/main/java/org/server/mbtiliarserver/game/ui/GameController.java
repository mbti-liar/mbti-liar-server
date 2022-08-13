package org.server.mbtiliarserver.game.ui;

import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.dto.GameRequest;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.server.mbtiliarserver.game.application.dto.PenaltiesResponse;
import org.server.mbtiliarserver.game.application.dto.ProgressResponse;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("games")
    public ResponseEntity<GameResponse> start(@RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gameService.start(gameRequest));
    }

    @PostMapping("votes/progress")
    public ResponseEntity<ProgressResponse> voteProgress(@RequestBody VotesRequest<Boolean> voteRequest) {
        return ResponseEntity.ok(gameService.voteProgress(voteRequest));
    }

    @PostMapping("votes/liar")
    public ResponseEntity<PenaltiesResponse> voteLiar(@RequestBody VotesRequest<Long> voteRequest) {
        return ResponseEntity.ok(gameService.voteLiar(voteRequest));
    }
}
