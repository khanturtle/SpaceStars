package com.spacestar.back.gamegenre.service;

import com.spacestar.back.gamegenre.dto.req.GameGenreReqDto;
import com.spacestar.back.gamegenre.dto.res.GameGenreResDto;

import java.util.List;

public interface GameGenreService {
    List<GameGenreResDto> getGameGenres();

    void addGameGenre(GameGenreReqDto gameGenreReqDto);

    void deleteGameGenre(Long gameGenreId);
}
