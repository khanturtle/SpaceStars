package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameClassResDto {
    private int index;
    private long gameClassId;
    private String gameClassName;
    private String gameClassNameKor;
    private String gameClassImage;
}
