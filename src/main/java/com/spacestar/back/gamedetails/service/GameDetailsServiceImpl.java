package com.spacestar.back.gamedetails.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.domain.GameTier;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
import com.spacestar.back.gamedetails.dto.res.GameTierResDto;
import com.spacestar.back.gamedetails.repository.GameClassRepository;
import com.spacestar.back.gamedetails.repository.GamePositionRepository;
import com.spacestar.back.gamedetails.repository.GameServerRepository;
import com.spacestar.back.gamedetails.repository.GameTierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameDetailsServiceImpl implements GameDetailsService {
    private final GameRepository gameRepository;
    private final GameClassRepository classRepository;
    private final GamePositionRepository positionRepository;
    private final GameServerRepository serverRepository;
    private final GameTierRepository tierRepository;

    @Override
    public List<GameClassResDto> getGameClass(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GameClass> gameClasses = classRepository.findByGame(game);
        List<GameClassResDto> gameClassResDtos = new ArrayList<>();

        for (int i = 0; i < gameClasses.size(); i++) {
            gameClassResDtos.add(
                    GameClassResDto.builder()
                            .index(i)
                            .gameClassId(gameClasses.get(i).getId())
                            .gameClassName(gameClasses.get(i).getGameClassName())
                            .gameClassImage(gameClasses.get(i).getGameClassImage())
                            .gameClassNameKor(gameClasses.get(i).getGameClassNameKor())
                            .build());
        }
        return gameClassResDtos;
    }

    @Override
    public List<GamePositionResDto> getGamePosition(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GamePosition> gamePositions = positionRepository.findByGame(game);
        List<GamePositionResDto> gamePositionResDtos = new ArrayList<>();

        for (int i = 0; i < gamePositions.size(); i++) {
            gamePositionResDtos.add(
                    GamePositionResDto.builder()
                            .index(i)
                            .gamePositionId(gamePositions.get(i).getId())
                            .gamePositionName(gamePositions.get(i).getGamePositionName())
                            .gamePositionImage(gamePositions.get(i).getGamePositionImage())
                            .gamePositionNameKor(gamePositions.get(i).getGamePositionNameKor())
                            .build());
        }
        return gamePositionResDtos;
    }

    @Override
    public List<GameServerResDto> getGameServer(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GameServer> gameServers = serverRepository.findByGame(game);
        List<GameServerResDto> gameServerResDtos = new ArrayList<>();

        for (int i = 0; i < gameServers.size(); i++) {
            gameServerResDtos.add(
                    GameServerResDto.builder()
                            .index(i)
                            .gameServerId(gameServers.get(i).getId())
                            .gameServerName(gameServers.get(i).getGameServerName())
                            .gameServerImage(gameServers.get(i).getGameServerImage())
                            .gameServerNameKor(gameServers.get(i).getGameServerNameKor())
                            .build());
        }
        return gameServerResDtos;
    }

    @Override
    public List<GameTierResDto> getGameTier(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GameTier> gameTiers = tierRepository.findByGame(game);
        List<GameTierResDto> gameTierResDtos = new ArrayList<>();

        for (int i = 0; i < gameTiers.size(); i++) {
            gameTierResDtos.add(
                    GameTierResDto.builder()
                            .index(i)
                            .gameTierId(gameTiers.get(i).getId())
                            .gameTierName(gameTiers.get(i).getGameTierName())
                            .gameTierImage(gameTiers.get(i).getGameTierImage())
                            .gameTierNameKor(gameTiers.get(i).getGameTierNameKor())
                            .build());
        }
        return gameTierResDtos;
    }
}
