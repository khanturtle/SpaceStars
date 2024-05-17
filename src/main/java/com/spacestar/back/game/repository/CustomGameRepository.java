package com.spacestar.back.game.repository;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.vo.res.GameResVo;

import java.util.List;

public interface CustomGameRepository {

    List<GameResVo> findAllGameNames();

    GameOptionResDto findGameOption(Long gameId);
}
