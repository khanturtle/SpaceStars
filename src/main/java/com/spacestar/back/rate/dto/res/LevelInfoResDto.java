package com.spacestar.back.rate.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LevelInfoResDto {
    private String levelIcon;
    private int levelTotalExp;
    private int levelNowExp;
}
