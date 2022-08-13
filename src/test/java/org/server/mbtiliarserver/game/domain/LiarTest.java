package org.server.mbtiliarserver.game.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiarTest {

    @Test
    void name() {
        Long id = 1L;

        Liar liar = new Liar(id);

        Assertions.assertThat(liar.getId()).isEqualTo(id);
    }
}