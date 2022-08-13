package org.server.mbtiliarserver.game.domain;

import org.server.mbtiliarserver.game.domain.Game;

import java.util.Optional;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findBySharingCode(String sharingCode);

    void delete(String sharingCode);
}
