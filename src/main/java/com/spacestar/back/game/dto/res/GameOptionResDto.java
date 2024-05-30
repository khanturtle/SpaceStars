package com.spacestar.back.game.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameOptionResDto {
    private Boolean isTier;
    private Boolean isPosition;
    private Boolean isClass;
    private Boolean isServer;

    public GameOptionResDto(Boolean isTier,Boolean isPosition,Boolean isClass,Boolean isServer) {
        this.isTier = isTier;
        this.isPosition = isPosition;
        this.isClass = isClass;
        this.isServer = isServer;
    }
}
