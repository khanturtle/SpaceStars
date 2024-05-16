package com.spacestar.back.game.service;

import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.game.vo.GameResVo;
import com.spacestar.back.gamegenre.repository.GameGenreRepository;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    @Override
    public ResponseEntity<List<GameResVo>> getGames() {
        List<GameResVo> gameResVos = gameRepository.findAllGameNames();
        for (int i = 0; i < gameResVos.size(); i++) {
            gameResVos.get(i).setIndex(i);
        }
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, gameResVos);
    }
}
