package org.server.mbtiliarserver.game.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PenaltyTest {

    @Test
    void name() {
        String 벌칙 = "소주 한잔 원샷";

        Penalty penalty = new Penalty(벌칙);

        Assertions.assertThat(penalty.getPenalty()).isEqualTo(벌칙);
    }
}