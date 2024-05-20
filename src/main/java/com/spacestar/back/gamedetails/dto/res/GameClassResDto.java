package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameClass;
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

    public static GameClassResDto toGameClassResDto(int index, GameClass gameClass) {
        return GameClassResDto.builder()
                .index(index)
                .gameClassId(gameClass.getId())
                .gameClassName(gameClass.getGameClassName())
                .gameClassImage(gameClass.getGameClassImage())
                .gameClassNameKor(gameClass.getGameClassNameKor())
                .build();
    }
}
