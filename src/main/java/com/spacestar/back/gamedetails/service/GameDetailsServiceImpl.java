package com.spacestar.back.gamedetails.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
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
                            .gameServerImage(gameServers.get(i).getGameServerImage())
                            .gameServerNameKor(gameServers.get(i).getGameServerNameKor())
                            .build());
        }
        return gameServerResDtos;
    }
}
