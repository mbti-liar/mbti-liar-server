package org.server.mbtiliarserver.game.application.dto;

import org.server.mbtiliarserver.game.domain.Decision;
import org.server.mbtiliarserver.game.domain.Judgment;
import org.server.mbtiliarserver.game.domain.Personality;
import org.server.mbtiliarserver.game.domain.Sense;

public class MbtiResponse {
    private final Personality personality;
    private final Sense sense;
    private final Decision decision;
    private final Judgment judgment;

    public MbtiResponse(Personality personality, Sense sense, Decision decision, Judgment judgment) {
        this.personality = personality;
        this.sense = sense;
        this.decision = decision;
        this.judgment = judgment;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Sense getSense() {
        return sense;
    }

    public Decision getDecision() {
        return decision;
    }

    public Judgment getJudgment() {
        return judgment;
    }
}
