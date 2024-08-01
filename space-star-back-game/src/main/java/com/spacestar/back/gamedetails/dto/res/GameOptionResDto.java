package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.domain.GameTier;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameOptionResDto {
    private long id;
    private String name;
    private String nameKor;
    private String image;

    public static GameOptionResDto toDto(GameClass gameClass) {
        return GameOptionResDto.builder()
                .id(gameClass.getId())
                .name(gameClass.getGameClassName())
                .image(gameClass.getGameClassImage())
                .nameKor(gameClass.getGameClassNameKor())
                .build();
    }
    public static GameOptionResDto toDto(GamePosition gamePosition) {
        return GameOptionResDto.builder()
                .id(gamePosition.getId())
                .name(gamePosition.getGamePositionName())
                .image(gamePosition.getGamePositionImage())
                .nameKor(gamePosition.getGamePositionNameKor())
                .build();
    }
    public static GameOptionResDto toDto(GameServer gameServer) {
        return GameOptionResDto.builder()
                .id(gameServer.getId())
                .name(gameServer.getGameServerName())
                .image(gameServer.getGameServerImage())
                .nameKor(gameServer.getGameServerNameKor())
                .build();
    }

    public static GameOptionResDto toDto(GameTier gameTier) {
        return GameOptionResDto.builder()
                .id(gameTier.getId())
                .name(gameTier.getGameTierName())
                .image(gameTier.getGameTierImage())
                .nameKor(gameTier.getGameTierNameKor())
                .build();
    }
}