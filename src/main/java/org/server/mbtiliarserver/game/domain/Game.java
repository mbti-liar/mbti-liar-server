package org.server.mbtiliarserver.game.domain;

import org.server.mbtiliarserver.game.exception.mbtiCardNotNullAndBlankException;

public class Game {

    private String mbtiCard;
    private Long liarId;
    private boolean gameProgress;

    public Game(String mbtiCard, Long liarId, boolean gameProgress) {
        if(mbtiCard == null || mbtiCard.isBlank()){
            throw new mbtiCardNotNullAndBlankException("mbtiCard는 비어 있을 수 없습니다.");
        }
        if(liarId == null){
            throw new IllegalArgumentException();
        }
        this.mbtiCard = mbtiCard;
        this.liarId = liarId;
        this.gameProgress = gameProgress;
    }

    public String getMbtiCard() {
        return mbtiCard;
    }

    public Long getLiarId() {
        return liarId;
    }

    public boolean isGameProgress() {
        return gameProgress;
    }
}
