package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GamePositionResDto {
    private int index;
    private long gamePositionId;
    private String gamePositionName;
    private String gamePositionNameKor;
    private String gamePositionImage;
}
