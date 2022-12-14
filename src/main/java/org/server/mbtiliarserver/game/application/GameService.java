package org.server.mbtiliarserver.game.application;

import org.server.mbtiliarserver.game.domain.Game;
import org.server.mbtiliarserver.game.domain.GameRepository;
import org.server.mbtiliarserver.game.domain.Liar;
import org.server.mbtiliarserver.game.domain.Penalty;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final String[] mbti = {
        "ENFJ", "ENTJ", "ENFP",
        "ENTP", "ESFP", "ESFJ",
        "ESTP", "ESTJ", "INFP",
        "INFJ", "INTP", "ISTP",
        "ISFP", "ISFJ", "ISTJ",
        "INTJ"};

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game create(String sharingCode) {
        double random = Math.random();
        int idx = (int) Math.round(random * (mbti.length - 1));
        return gameRepository.save(new Game(mbti[idx], null, true, 0L, sharingCode));
    }

    public void delete(String sharingCode) {
        gameRepository.delete(sharingCode);
    }

    public Optional<Game> findGame(String sharingCode) {
        return gameRepository.findBySharingCode(sharingCode);
    }

    public Liar selectLiar(String sharingCode) {
        Game game = gameRepository.findBySharingCode(sharingCode).orElseThrow(IllegalArgumentException::new);
        Random random = new Random();
        int liarIdx = random.nextInt(game.getParticipants().size());
        return new Liar(game.getParticipants().get(liarIdx).getId());
    }

    public Penalty getPenalty() {
        return new Penalty("옆 사람이 벌칙 정해주기~");
    }
}
