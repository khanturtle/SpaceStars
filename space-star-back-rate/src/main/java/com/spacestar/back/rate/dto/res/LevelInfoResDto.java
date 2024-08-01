package com.spacestar.back.rate.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelInfoResDto {
    private String levelIcon;
    private int levelTotalExp;
}
