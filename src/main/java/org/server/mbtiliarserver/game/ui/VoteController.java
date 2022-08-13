package org.server.mbtiliarserver.game.ui;

import org.server.mbtiliarserver.game.application.VoterService;
import org.server.mbtiliarserver.game.application.dto.PenaltiesResponse;
import org.server.mbtiliarserver.game.application.dto.ProgressResponse;
import org.server.mbtiliarserver.game.application.dto.VotesRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("votes")
public class VoteController {
    private final VoterService voterService;

    public VoteController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("progress")
    public ResponseEntity<ProgressResponse> voteProgress(@RequestBody VotesRequest<Boolean> voteRequest) {
        return ResponseEntity.ok(voterService.voteProgress(voteRequest));
    }

    @PostMapping("liar")
    public ResponseEntity<PenaltiesResponse> voteLiar(@RequestBody VotesRequest<Long> voteRequest) {
        return ResponseEntity.ok(voterService.voteLiar(voteRequest));
    }
}
