package com.spacestar.back.gamegenre.dto.res;

import com.spacestar.back.gamegenre.domain.GameGenre;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameGenreResDto {
    private int index;
    private long genreId;
    private String genre;

    public static GameGenreResDto toDto(int index,GameGenre gameGenre){
        return GameGenreResDto.builder()
                .index(index)
                .genreId(gameGenre.getId())
                .genre(gameGenre.getGenre())
                .build();
    }
}
