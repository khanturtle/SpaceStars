package com.spacestar.back.game.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.game.vo.GameOptionResVo;
import com.spacestar.back.game.vo.GameResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public ResponseEntity<List<GameResVo>> getGames() {
        List<GameResVo> gameResVos = gameRepository.findAllGameNames();
        for (int i = 0; i < gameResVos.size(); i++) {
            gameResVos.get(i).setIndex(i);
        }
        return new ResponseEntity<List<GameResVo>>(ResponseSuccess.GET_GAMES_SUCCESS, gameResVos);
    }

    @Override
    public ResponseEntity<GameOptionResVo> getGameOption(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();

        return new ResponseEntity<GameOptionResVo>(ResponseSuccess.GET_GAME_OPTION_SUCCESS, new GameOptionResVo(game));
    }
}
