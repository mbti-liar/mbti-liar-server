package org.server.mbtiliarserver.game.application;

import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.GameRepository;
import org.server.mbtiliarserver.game.domain.Liar;
import org.server.mbtiliarserver.game.domain.Penalty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create() {
        String uuid = UUID.randomUUID().toString();
        return gameRepository.save(new Game("INFJ", null, true, new ArrayList<>(), new ArrayList<>(), 0L, new ArrayList<>(), new ArrayList<>(), uuid.substring(0, 5)));
    }

    public void delete(String sharingCode) {
        gameRepository.delete(sharingCode);
    }

    public Game findGame(String sharingCode) {
        return gameRepository.findBySharingCode(sharingCode).orElseThrow(IllegalArgumentException::new);
    }

    public Liar selectLiar(String sharingCode) {
        Game game = gameRepository.findBySharingCode(sharingCode).orElseThrow(IllegalArgumentException::new);
        return new Liar(game.getParticipants().get(0).getId());
    }

    public Penalty getPenalty() {
        return null;
    }
}
