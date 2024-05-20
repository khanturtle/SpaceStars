package com.spacestar.back.gamegenre.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameGenreResDto {
    private int index;
    private long genreId;
    private String genre;
}
