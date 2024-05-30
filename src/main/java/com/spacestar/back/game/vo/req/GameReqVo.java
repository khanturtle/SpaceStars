package com.spacestar.back.game.vo.req;

import lombok.Getter;

@Getter
public class GameReqVo {
    private String name;
    private String nameKor;
    private String image;
    private boolean isTier;
    private boolean isPosition;
    private boolean isClass;
    private boolean isServer;
}
