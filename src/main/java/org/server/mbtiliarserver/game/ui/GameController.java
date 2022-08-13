package org.server.mbtiliarserver.game.ui;

import org.server.mbtiliarserver.game.application.GameService;
import org.server.mbtiliarserver.game.application.dto.GameRequest;
import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.server.mbtiliarserver.game.application.dto.GameRoomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/{sharingCode}")
    public ResponseEntity<GameResponse> start(@PathVariable String sharingCode) {
        return ResponseEntity.ok(gameService.start(sharingCode));
    }

    @PostMapping("rooms")
    public ResponseEntity<Void> create() {
        GameRoomResponse gameResponse = gameService.create();
        return ResponseEntity.created(URI.create("/games/" + gameResponse.getSharingCode())).build();
    }

    @GetMapping("rooms/{sharingCode}")
    public ResponseEntity<Void> entrance(@PathVariable String sharingCode) {
        gameService.entrance(sharingCode);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("rooms/{sharingCode}")
    public ResponseEntity<Void> end(@PathVariable String sharingCode) {
        gameService.delete(sharingCode);
        return ResponseEntity.ok().build();
    }
}
