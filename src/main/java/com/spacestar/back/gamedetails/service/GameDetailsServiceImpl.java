package com.spacestar.back.gamedetails.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import com.spacestar.back.gamedetails.domain.GameTier;
import com.spacestar.back.gamedetails.dto.req.GameClassReqDto;
import com.spacestar.back.gamedetails.dto.req.GamePositionReqDto;
import com.spacestar.back.gamedetails.dto.req.GameServerReqDto;
import com.spacestar.back.gamedetails.dto.req.GameTierReqDto;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
import com.spacestar.back.gamedetails.dto.res.GameTierResDto;
import com.spacestar.back.gamedetails.repository.GameClassRepository;
import com.spacestar.back.gamedetails.repository.GamePositionRepository;
import com.spacestar.back.gamedetails.repository.GameServerRepository;
import com.spacestar.back.gamedetails.repository.GameTierRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional(readOnly = true)
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

        return IntStream.range(0, gameClasses.size())
                .mapToObj(i -> GameClassResDto.toGameClassResDto(i, gameClasses.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public List<GamePositionResDto> getGamePosition(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GamePosition> gamePositions = positionRepository.findByGame(game);

        return IntStream.range(0, gamePositions.size())
                .mapToObj(i -> GamePositionResDto.toGamePositionResDto(i, gamePositions.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameServerResDto> getGameServer(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GameServer> gameServers = serverRepository.findByGame(game);

        return IntStream.range(0, gameServers.size())
                .mapToObj(i -> GameServerResDto.toGameServerResDto(i, gameServers.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameTierResDto> getGameTier(Long gameId) {
        Game game = gameRepository.getReferenceById(gameId);
        List<GameTier> gameTiers = tierRepository.findByGame(game);

        return IntStream.range(0, gameTiers.size())
                .mapToObj(i -> GameTierResDto.toGameTierResDto(i, gameTiers.get(i)))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addGameClass(Long gameId, GameClassReqDto gameClassReqDto) {
        Game game = gameRepository.getReferenceById(gameId);
        if (!game.isClass()) {
            throw new GlobalException(ResponseStatus.GAME_CLASS_NOT_FOUND);
        }
        classRepository.save(GameClass.builder()
                .game(game)
                .gameClassImage(gameClassReqDto.getGameClassImage())
                .gameClassName(gameClassReqDto.getGameClassName())
                .gameClassNameKor(gameClassReqDto.getGameClassNameKor())
                .build());
    }

    @Transactional
    @Override
    public void deleteGameClass(Long classId) {
        classRepository.deleteById(classId);
    }

    @Transactional
    @Override
    public void addGamePosition(Long gameId, GamePositionReqDto gamePositionReqDto) {
        Game game = gameRepository.getReferenceById(gameId);
        if (!game.isPosition()) {
            throw new GlobalException(ResponseStatus.GAME_POSITION_NOT_FOUND);
        }
        positionRepository.save(GamePosition.builder()
                .game(game)
                .gamePositionImage(gamePositionReqDto.getGamePositionImage())
                .gamePositionName(gamePositionReqDto.getGamePositionName())
                .gamePositionNameKor(gamePositionReqDto.getGamePositionNameKor())
                .build());
    }

    @Transactional
    @Override
    public void deleteGamePosition(Long positionId) {
        positionRepository.deleteById(positionId);
    }

    @Transactional
    @Override
    public void addGameServer(Long gameId, GameServerReqDto gameServerReqDto) {
        Game game = gameRepository.getReferenceById(gameId);
        if (!game.isServer()) {
            throw new GlobalException(ResponseStatus.GAME_SERVER_NOT_FOUND);
        }
        serverRepository.save(GameServer.builder()
                .game(game)
                .gameServerImage(gameServerReqDto.getGameServerImage())
                .gameServerName(gameServerReqDto.getGameServerName())
                .gameServerNameKor(gameServerReqDto.getGameServerNameKor())
                .build());
    }

    @Transactional
    @Override
    public void deleteGameServer(Long serverId) {
        serverRepository.deleteById(serverId);
    }
    @Transactional
    @Override
    public void addGameTier(Long gameId, GameTierReqDto gameTierReqDto) {
        Game game = gameRepository.getReferenceById(gameId);
        if (!game.isTier()) {
            throw new GlobalException(ResponseStatus.GAME_TIER_NOT_FOUND);
        }
        tierRepository.save(GameTier.builder()
                .game(game)
                .gameTierImage(gameTierReqDto.getGameTierImage())
                .gameTierName(gameTierReqDto.getGameTierName())
                .gameTierNameKor(gameTierReqDto.getGameTierNameKor())
                .build());
    }
    @Transactional
    @Override
    public void deleteGameTier(Long tierId) {
        tierRepository.deleteById(tierId);
    }
}
