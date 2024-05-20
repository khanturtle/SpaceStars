package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameTierResDto {
    private int index;
    private long gameTierId;
    private String gameTierName;
    private String gameTierNameKor;
    private String gameTierImage;
}
