package com.spacestar.back.game.service;

import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public List<GameResDto> getGames() {
        return gameRepository.findAllGameNames();
    }

    @Override
    public GameOptionResDto getGameOption(Long gameId) {
        return gameRepository.findGameOption(gameId).orElseThrow(
                ()-> new GlobalException(ResponseStatus.GAME_NOT_FOUND)
        );
    }
}
