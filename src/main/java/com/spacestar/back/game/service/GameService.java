package com.spacestar.back.game.service;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.global.ResponseEntity;

import java.util.List;

public interface GameService {
    ResponseEntity<List<GameResVo>> getGames();

    GameOptionResDto getGameOption(Long gameId);
}
