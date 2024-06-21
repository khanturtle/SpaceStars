package com.spacestar.back.gamedetails.service;

import com.spacestar.back.gamedetails.dto.req.GameClassReqDto;
import com.spacestar.back.gamedetails.dto.req.GamePositionReqDto;
import com.spacestar.back.gamedetails.dto.req.GameServerReqDto;
import com.spacestar.back.gamedetails.dto.req.GameTierReqDto;
import com.spacestar.back.gamedetails.dto.res.*;

import java.util.List;

public interface GameDetailsService {
    List<GameClassResDto> getGameClass(Long gameId);

    List<GamePositionResDto> getGamePosition(Long gameId);

    List<GameServerResDto> getGameServer(Long gameId);

    List<GameTierResDto> getGameTier(Long gameId);

    void addGameClass(Long gameId, GameClassReqDto gameClassReqDto);

    void deleteGameClass(Long classId);

    void addGamePosition(Long gameId, GamePositionReqDto gamePositionReqDto);

    void deleteGamePosition(Long positionId);

    void addGameServer(Long gameId, GameServerReqDto gameServerReqDto);

    void deleteGameServer(Long serverId);

    void addGameTier(Long gameId, GameTierReqDto gameTierReqDto);

    void deleteGameTier(Long tierId);

    GameOptionResDto getGameClassDetail(Long optionId);

    GameOptionResDto getGamePositionDetail(Long optionId);
}
