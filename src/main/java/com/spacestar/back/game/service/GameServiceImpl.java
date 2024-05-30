package com.spacestar.back.game.service;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.game.dto.req.GameReqDto;
import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.repository.GameRepository;
import com.spacestar.back.gamegenre.domain.GameGenre;
import com.spacestar.back.gamegenre.repository.GameGenreRepository;
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
private final GameGenreRepository gameGenreRepository;
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

    @Override
    public void addGame(Long gameGenreId, GameReqDto gameReqDto) {
        GameGenre gameGenre = gameGenreRepository.findById(gameGenreId).orElseThrow(
                ()->new GlobalException(ResponseStatus.GAME_GENRE_NOT_FOUND)
        );
        gameRepository.save(Game.builder()
                .gameGenre(gameGenre)
                .name(gameReqDto.getName())
                .image(gameReqDto.getImage())
                .name_kor(gameReqDto.getName_kor())
                .isClass(gameReqDto.isClass())
                .isPosition(gameReqDto.isPosition())
                .isServer(gameReqDto.isServer())
                .isTier(gameReqDto.isTier())
                .build());
    }
}
