package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameTier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameTierResDto {
    private int index;
    private long id;
    private String name;
    private String nameKor;
    private String image;
    public static GameTierResDto toGameTierResDto(int index, GameTier gameTier) {
        return GameTierResDto.builder()
                .index(index)
                .id(gameTier.getId())
                .name(gameTier.getGameTierName())
                .image(gameTier.getGameTierImage())
                .nameKor(gameTier.getGameTierNameKor())
                .build();
    }
}
