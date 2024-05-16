package com.spacestar.back.game.repository;

import com.spacestar.back.game.vo.GameResVo;

import java.util.List;

public interface CustomGameRepository {

    List<GameResVo> findAllGameNames();
}
