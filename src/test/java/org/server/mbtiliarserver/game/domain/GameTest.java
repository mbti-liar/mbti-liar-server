package org.server.mbtiliarserver.game.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GameTest {

    @Test
    void name() {
        String decision = Decision.THINKING.getType();
        String judgement = Judgment.JUDGING.getType();
        String personality = Personality.EXTROVERSION.getType();
        String sense = Sense.SENSING.getType();

        String mbtiCard = decision + judgement + personality + sense;
        Long liarId = 1L;
        boolean gameProgress = true;

        Game game = new Game(mbtiCard, liarId, gameProgress, Collections.emptyList());

        Assertions.assertThat(game.getMbtiCard()).isEqualTo(mbtiCard);
        Assertions.assertThat(game.getLiarId()).isEqualTo(liarId);
        Assertions.assertThat(game.isGameProgress()).isEqualTo(gameProgress);
    }

}
