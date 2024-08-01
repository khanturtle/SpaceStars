package com.spacestar.back.gamegenre.service;

import com.spacestar.back.gamegenre.domain.GameGenre;
import com.spacestar.back.gamegenre.dto.req.GameGenreReqDto;
import com.spacestar.back.gamegenre.dto.res.GameGenreResDto;
import com.spacestar.back.gamegenre.repository.GameGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GameGenreServiceImpl implements GameGenreService{
    private final GameGenreRepository gameGenreRepository;
    @Override
    public List<GameGenreResDto> getGameGenres() {
        List<GameGenre> gameGenres = gameGenreRepository.findAll();

        return  IntStream.range(0, gameGenres.size())
                .mapToObj(i -> GameGenreResDto.toDto(i, gameGenres.get(i)))
                .toList();
    }

    @Transactional
    @Override
    public void addGameGenre(GameGenreReqDto gameGenreReqDto) {
        gameGenreRepository.save(GameGenre.builder()
                .genre(gameGenreReqDto.getGenre())
                .build());
    }

    @Transactional
    @Override
    public void deleteGameGenre(Long gameGenreId) {
        gameGenreRepository.deleteById(gameGenreId);
    }
}
