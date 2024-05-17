package com.spacestar.back.game.repository;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.vo.res.GameResVo;

import java.util.List;
import java.util.Optional;

public interface CustomGameRepository {

    List<GameResVo> findAllGameNames();

    Optional<GameOptionResDto> findGameOption(Long gameId);
}
