package com.spacestar.back.gamegenre.controller;

import com.spacestar.back.gamegenre.dto.req.GameGenreReqDto;
import com.spacestar.back.gamegenre.dto.res.GameGenreResDto;
import com.spacestar.back.gamegenre.service.GameGenreService;
import com.spacestar.back.gamegenre.vo.req.GameGenreReqVo;
import com.spacestar.back.gamegenre.vo.res.GameGenreResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "GameGenre", description = "게임 장르")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game/genre")
public class GameGenreController {
    private final GameGenreService gameGenreService;
    private final ModelMapper modelMapper;
    @Operation(summary = "게임 장르 전체 조회")
    @GetMapping
    public ResponseEntity<List<GameGenreResVo>> getGameGenres() {
        List<GameGenreResDto> gameGenreResDtos = gameGenreService.getGameGenres();
        List<GameGenreResVo> gameGenreResVos = gameGenreResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameGenreResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_GENRE_SUCCESS,gameGenreResVos);
    }

    @PostMapping
    public ResponseEntity<Void> addGameGenre(@RequestBody GameGenreReqVo gameGenreReqVo){
        gameGenreService.addGameGenre(modelMapper.map(gameGenreReqVo, GameGenreReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.GAME_GENRE_ADD_SUCCESS,null);
    }

    @DeleteMapping("/{gameGenreId}")
    public ResponseEntity<Void> deleteGameGenre(@PathVariable Long gameGenreId){
        gameGenreService.deleteGameGenre(gameGenreId);
        return new ResponseEntity<>(ResponseSuccess.GAME_GENRE_DELETE_SUCCESS,null);
    }
}
