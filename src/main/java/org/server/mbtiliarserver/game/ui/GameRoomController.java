package org.server.mbtiliarserver.game.ui;

import org.server.mbtiliarserver.game.application.GameRoomService;
import org.server.mbtiliarserver.game.application.dto.GameRoomResponse;
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
public class GameRoomController {

    private final GameRoomService gameService;

    public GameRoomController(GameRoomService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Void> create() {
        GameRoomResponse gameResponse = gameService.create();
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
