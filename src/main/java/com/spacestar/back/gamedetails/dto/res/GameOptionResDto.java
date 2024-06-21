package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameClass;
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
}