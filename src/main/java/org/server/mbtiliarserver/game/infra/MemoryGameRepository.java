package org.server.mbtiliarserver.game.infra;

import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.GameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryGameRepository implements GameRepository {

    private final static Map<String, Game> gameMap = new HashMap<>();

    @Override
    public Game save(Game game) {
        return gameMap.put(game.getSharingCode(), game);
    }

    @Override
    public Optional<Game> findBySharingCode(String sharingCode) {
        return Optional.of(gameMap.get(sharingCode));
    }
}