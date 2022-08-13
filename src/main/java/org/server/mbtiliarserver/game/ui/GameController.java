package org.server.mbtiliarserver.game.ui;

import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        GameResponse gameResponse = gameService.create();
        return ResponseEntity.created(URI.create("/games/" + gameResponse.getSharingCode())).build();
    }

    @GetMapping("/{sharingCode}")
    public ResponseEntity<Void> entrance(@PathVariable String sharingCode) {
        gameService.entrance(sharingCode);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> end() {
        gameService.delete();
        return ResponseEntity.ok().build();
    }
}
