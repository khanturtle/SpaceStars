package com.spacestar.back.game.service;

import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.game.vo.GameResVo;
import com.spacestar.back.gamegenre.repository.GameGenreRepository;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    @Override
    public ResponseEntity<?> getGames() {
        return new ResponseEntity<>(ResponseSuccess.SUCCESS,
                gameRepository.findAllGameNames());
    }
}
