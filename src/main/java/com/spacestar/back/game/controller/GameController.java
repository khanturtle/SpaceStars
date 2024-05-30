package com.spacestar.back.game.controller;

import com.spacestar.back.game.dto.req.GameReqDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.service.GameService;
import com.spacestar.back.game.vo.req.GameReqVo;
import com.spacestar.back.game.vo.res.GameOptionResVo;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<GameResVo>> getGames() {
        List<GameResDto> gameResDtos = gameService.getGames();
        List<GameResVo> gameResVos = gameResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAMES_SUCCESS, gameResVos);
    }

    @GetMapping("/option/{gameId}")
    public ResponseEntity<GameOptionResVo> getGameOption(@PathVariable Long gameId) {
        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_OPTION_SUCCESS
                , modelMapper.map(gameService.getGameOption(gameId), GameOptionResVo.class));
    }

    @PostMapping("/{gameGenreId}")
    public ResponseEntity<Void> addGame(@PathVariable Long gameGenreId,
                                        @RequestBody GameReqVo gameReqVo) {
        gameService.addGame(gameGenreId, modelMapper.map(gameReqVo, GameReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.GAME_ADD_SUCCESS, null);
    }
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId){
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(ResponseSuccess.GAME_DELETE_SUCCESS,null);
    }
}
