package com.spacestar.back.game.service;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.global.ResponseEntity;

import java.util.List;

public interface GameService {
    List<GameResDto> getGames();

    GameOptionResDto getGameOption(Long gameId);
}
