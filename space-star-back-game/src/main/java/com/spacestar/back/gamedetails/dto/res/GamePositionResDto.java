package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GamePosition;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GamePositionResDto {
    private int index;
    private long id;
    private String name;
    private String nameKor;
    private String image;
    public static GamePositionResDto toGamePositionResDto(int index, GamePosition gamePosition) {
        return GamePositionResDto.builder()
                .index(index)
                .id(gamePosition.getId())
                .name(gamePosition.getGamePositionName())
                .image(gamePosition.getGamePositionImage())
                .nameKor(gamePosition.getGamePositionNameKor())
                .build();
    }
}
