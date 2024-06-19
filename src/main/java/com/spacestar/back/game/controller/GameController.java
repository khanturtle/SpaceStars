package com.spacestar.back.game.controller;

import com.spacestar.back.game.dto.req.GameReqDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.dto.res.GameResDto2;
import com.spacestar.back.game.service.GameService;
import com.spacestar.back.game.vo.req.GameReqVo;
import com.spacestar.back.game.vo.res.GameOptionResVo;
import com.spacestar.back.game.vo.res.GameResVo;
import com.spacestar.back.game.vo.res.GameResVo2;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Game", description = "게임")
@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Operation(summary = "게임 전체 조회")
    @GetMapping
    public ResponseEntity<List<GameResVo>> getGames() {
        List<GameResDto> gameResDtos = gameService.getGames();
        List<GameResVo> gameResVos = gameResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAMES_SUCCESS, gameResVos);
    }
    @Operation(summary = "게임 단건 조회")
    @GetMapping("/{gameId}")
    public ResponseEntity<GameResVo2> getGame(@PathVariable Long gameId) {
        GameResDto2 gameResDto = gameService.getGame(gameId);
        GameResVo2 gameResVo = modelMapper.map(gameResDto, GameResVo2.class);

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_SUCCESS, gameResVo);
    }
    @Operation(summary = "게임 옵션 유무 조회")
    @GetMapping("/option/{gameId}")
    public ResponseEntity<GameOptionResVo> getGameOption(@PathVariable Long gameId) {
        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_OPTION_SUCCESS
                , modelMapper.map(gameService.getGameOption(gameId), GameOptionResVo.class));
    }
    @Operation(summary = "게임 생성")
    @PostMapping("/{gameGenreId}")
    public ResponseEntity<Void> addGame(@PathVariable Long gameGenreId,
                                        @RequestBody GameReqVo gameReqVo) {
        gameService.addGame(gameGenreId, modelMapper.map(gameReqVo, GameReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.GAME_ADD_SUCCESS, null);
    }
    @Operation(summary = "게임 삭제")
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId){
        gameService.deleteGame(gameId);
        return new ResponseEntity<>(ResponseSuccess.GAME_DELETE_SUCCESS,null);
    }
}
