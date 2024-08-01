package com.spacestar.back.game.convertor;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.dto.req.GameReqDto;
import com.spacestar.back.gamegenre.domain.GameGenre;

public class GameConvertor {
    public static Game toEntity(GameGenre gameGenre, GameReqDto gameReqDto) {
        return Game.builder()
                .gameGenre(gameGenre)
                .name(gameReqDto.getName())
                .gameLogoImage(gameReqDto.getGameLogoImage())
                .gameImage(gameReqDto.getGameImage())
                .nameKor(gameReqDto.getNameKor())
                .isClass(gameReqDto.isClass())
                .isPosition(gameReqDto.isPosition())
                .isServer(gameReqDto.isServer())
                .isTier(gameReqDto.isTier())
                .build();
    }
}
