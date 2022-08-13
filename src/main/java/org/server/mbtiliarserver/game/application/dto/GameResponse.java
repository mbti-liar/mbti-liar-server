package org.server.mbtiliarserver.game.application.dto;

public class GameResponse {
    private final LiarResponse liarResponse;
    private final MbtiResponse mbtiResponse;
    private final ParticipantsResponse participantsResponse;

    public GameResponse(LiarResponse liarResponse, MbtiResponse mbtiResponse, ParticipantsResponse participantsResponse) {
        this.liarResponse = liarResponse;
        this.mbtiResponse = mbtiResponse;
        this.participantsResponse = participantsResponse;
    }

    public LiarResponse getLiarResponse() {
        return liarResponse;
    }

    public MbtiResponse getMbtiResponse() {
        return mbtiResponse;
    }

    public ParticipantsResponse getParticipantsResponse() {
        return participantsResponse;
    }
}
