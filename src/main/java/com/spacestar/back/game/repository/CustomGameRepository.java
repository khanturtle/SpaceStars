package com.spacestar.back.game.repository;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;

import java.util.List;
import java.util.Optional;

public interface CustomGameRepository {

    List<GameResDto> findAllGameNames();

    Optional<GameOptionResDto> findGameOption(Long gameId);
}
