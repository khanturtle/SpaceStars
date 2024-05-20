package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
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
    public static GamePositionResDto toGamePositionResDto(int index, GamePosition gamePosition) {
        return GamePositionResDto.builder()
                .index(index)
                .gamePositionId(gamePosition.getId())
                .gamePositionName(gamePosition.getGamePositionName())
                .gamePositionImage(gamePosition.getGamePositionImage())
                .gamePositionNameKor(gamePosition.getGamePositionNameKor())
                .build();
    }
}
