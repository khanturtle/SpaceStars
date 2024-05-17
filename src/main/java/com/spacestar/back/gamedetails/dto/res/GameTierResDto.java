package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameTierResDto {
    private int index;
    private String gameTierNameKor;
    private String gameTierImage;
}
