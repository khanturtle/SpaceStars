package com.spacestar.back.game.dto.req;

import lombok.Getter;

@Getter
public class GameReqDto {
    private String name;
    private String name_kor;
    private String image;
    private boolean isTier;
    private boolean isPosition;
    private boolean isClass;
    private boolean isServer;
}
