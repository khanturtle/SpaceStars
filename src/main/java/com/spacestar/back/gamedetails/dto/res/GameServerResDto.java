package com.spacestar.back.gamedetails.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameServerResDto {
    private int index;
    private String gameServerNameKor;
    private String gameServerImage;
}
