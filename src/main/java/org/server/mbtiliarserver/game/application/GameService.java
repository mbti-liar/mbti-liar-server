package org.server.mbtiliarserver.game.application;

import org.server.mbtiliarserver.game.application.dto.GameResponse;
import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameResponse start(String sharingCode) {
        return null;
    }

    public Game create() {
        String uuid = UUID.randomUUID().toString();
        return new Game(uuid.substring(0, 5));
    }

    public void entrance(String sharingCode) {

    }

    public void delete(String sharingCode) {

    }

    public Long findAmount(String sharingCode) {
        return null;
    }

    public Game findGame(String sharingCode) {
        return null;
    }
}
