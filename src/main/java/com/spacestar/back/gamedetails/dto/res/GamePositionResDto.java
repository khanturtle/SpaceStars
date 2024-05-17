package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GamePositionResDto {
    private int index;
    private String gamePositionNameKor;
    private String gamePositionImage;
}
