package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameClass;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameClassResDto {
    private int index;
    private long id;
    private String name;
    private String nameKor;
    private String image;

    public static GameClassResDto toGameClassResDto(int index, GameClass gameClass) {
        return GameClassResDto.builder()
                .index(index)
                .id(gameClass.getId())
                .name(gameClass.getGameClassName())
                .image(gameClass.getGameClassImage())
                .nameKor(gameClass.getGameClassNameKor())
                .build();
    }
}
