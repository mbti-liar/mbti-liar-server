package org.server.mbtiliarserver.game.infra;

import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.GameRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryGameRepository implements GameRepository {

    private final static Map<String, Game> gameMap = new ConcurrentHashMap<>();

    @Override
    public Game save(Game game) {
        return gameMap.put(game.getSharingCode(), game);
    }

    @Override
    public Optional<Game> findBySharingCode(String sharingCode) {
        return Optional.ofNullable(gameMap.get(sharingCode));
    }

    @Override
    public void delete(String sharingCode) {
        gameMap.remove(sharingCode);
    }
}
