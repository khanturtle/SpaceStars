package com.spacestar.back.gamedetails.service;

import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;

import java.util.List;

public interface GameDetailsService {
    List<GameClassResDto> getGameClass(Long gameId);

    List<GamePositionResDto> getGamePosition(Long gameId);

    List<GameServerResDto> getGameServer(Long gameId);
}
