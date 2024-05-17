package com.spacestar.back.gamedetails.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.gamedetails.domain.GameClass;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
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
}
