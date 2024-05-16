package com.spacestar.back.game.service;

import com.spacestar.back.game.vo.GameResVo;
import com.spacestar.back.global.ResponseEntity;

import java.util.List;

public interface GameService {
    ResponseEntity<List<GameResVo>> getGames();
}
