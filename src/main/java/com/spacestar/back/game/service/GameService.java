package com.spacestar.back.game.service;

import com.spacestar.back.game.dto.req.GameReqDto;
import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;

import java.util.List;

public interface GameService {
    List<GameResDto> getGames();

    GameOptionResDto getGameOption(Long gameId);

    void addGame(Long gameGenreId, GameReqDto gameReqDto);
}
