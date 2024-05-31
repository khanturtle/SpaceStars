package com.spacestar.back.gamedetails.service;

import com.spacestar.back.gamedetails.dto.req.GameClassReqDto;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
import com.spacestar.back.gamedetails.dto.res.GameTierResDto;

import java.util.List;

public interface GameDetailsService {
    List<GameClassResDto> getGameClass(Long gameId);

    List<GamePositionResDto> getGamePosition(Long gameId);

    List<GameServerResDto> getGameServer(Long gameId);

    List<GameTierResDto> getGameTier(Long gameId);

    void addGameClass(Long gameId, GameClassReqDto gameClassReqDto);

    void deleteGameClass(Long classId);
}
