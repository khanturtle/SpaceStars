package com.spacestar.back.game.dto.req;

import lombok.Getter;

@Getter
public class GameReqDto {
    private String name;
    private String nameKor;
    private String gameLogoImage;
    private String gameImage;
    private boolean isTier;
    private boolean isPosition;
    private boolean isClass;
    private boolean isServer;
}
