package com.spacestar.back.gamegenre.service;

import com.spacestar.back.gamegenre.dto.res.GameGenreResDto;
import com.spacestar.back.gamegenre.repository.GameGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameGenreServiceImpl implements GameGenreService{
    private final GameGenreRepository gameGenreRepository;
    @Override
    public List<GameGenreResDto> getGameGenres() {

        return null;
    }
}
