package com.spacestar.back.gamedetails.service;

import com.spacestar.back.gamedetails.dto.res.GameClassResDto;

import java.util.List;

public interface GameDetailsService {
    List<GameClassResDto> getGameClass(Long gameId);
}
