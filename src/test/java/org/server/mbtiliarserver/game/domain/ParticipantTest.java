package org.server.mbtiliarserver.game.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    void name() {
        Long id = 1L;
        String 참가자 = "최정은";

        Participant participant = new Participant(id, 참가자);

        Assertions.assertThat(participant.getId()).isEqualTo(id);
        Assertions.assertThat(participant.getParticipant()).isEqualTo(참가자);
    }
}