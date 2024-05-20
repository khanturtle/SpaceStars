package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.domain.GameTier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameTierResDto {
    private int index;
    private long gameTierId;
    private String gameTierName;
    private String gameTierNameKor;
    private String gameTierImage;
    public static GameTierResDto toGameTierResDto(int index, GameTier gameTier) {
        return GameTierResDto.builder()
                .index(index)
                .gameTierId(gameTier.getId())
                .gameTierName(gameTier.getGameTierName())
                .gameTierImage(gameTier.getGameTierImage())
                .gameTierNameKor(gameTier.getGameTierNameKor())
                .build();
    }
}
