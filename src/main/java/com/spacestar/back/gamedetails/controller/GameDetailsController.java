package com.spacestar.back.gamedetails.controller;

import com.spacestar.back.gamedetails.dto.req.GamePositionReqDto;
import com.spacestar.back.gamedetails.dto.req.GameClassReqDto;
import com.spacestar.back.gamedetails.dto.req.GameServerReqDto;
import com.spacestar.back.gamedetails.dto.req.GameTierReqDto;
import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
import com.spacestar.back.gamedetails.dto.res.GameTierResDto;
import com.spacestar.back.gamedetails.service.GameDetailsService;
import com.spacestar.back.gamedetails.vo.req.GameClassReqVo;
import com.spacestar.back.gamedetails.vo.req.GamePositionReqVo;
import com.spacestar.back.gamedetails.vo.req.GameServerReqVo;
import com.spacestar.back.gamedetails.vo.req.GameTierReqVo;
import com.spacestar.back.gamedetails.vo.res.GameTierResVo;
import com.spacestar.back.gamedetails.vo.res.GameClassResVo;
import com.spacestar.back.gamedetails.vo.res.GamePositionResVo;
import com.spacestar.back.gamedetails.vo.res.GameServerResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Game Details", description = "게임 세부 정보")
@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameDetailsController {
    private final ModelMapper modelMapper;
    private final GameDetailsService gameDetailsService;
    @Operation(summary = "게임 직업 조회")
    @GetMapping("/class/{gameId}")
    public ResponseEntity<List<GameClassResVo>> getGameClass(@PathVariable Long gameId) {
        List<GameClassResDto> gameClassResDtos = gameDetailsService.getGameClass(gameId);

        List<GameClassResVo> gameClassResVos = gameClassResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameClassResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS, gameClassResVos);
    }
    @Operation(summary = "게임 포지션 조회")
    @GetMapping("/position/{gameId}")
    public ResponseEntity<List<GamePositionResVo>> getGamePosition(@PathVariable Long gameId) {
        List<GamePositionResDto> gamePositionResDtos = gameDetailsService.getGamePosition(gameId);

        List<GamePositionResVo> gamePositionResVos = gamePositionResDtos.stream()
                .map(dto -> modelMapper.map(dto, GamePositionResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_POSITION_SUCCESS, gamePositionResVos);
    }
    @Operation(summary = "게임 서버 조회")
    @GetMapping("/server/{gameId}")
    public ResponseEntity<List<GameServerResVo>> getGameServer(@PathVariable Long gameId) {
        List<GameServerResDto> gameServerResDtos = gameDetailsService.getGameServer(gameId);

        List<GameServerResVo> gameServerResVos = gameServerResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameServerResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_SERVER_SUCCESS, gameServerResVos);
    }
    @Operation(summary = "게임 티어 조회")
    @GetMapping("/tier/{gameId}")
    public ResponseEntity<List<GameTierResVo>> getGameTier(@PathVariable Long gameId) {
        List<GameTierResDto> gameTierResDtos = gameDetailsService.getGameTier(gameId);

        List<GameTierResVo> gameTierResVos = gameTierResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameTierResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_TIER_SUCCESS, gameTierResVos);
    }
    @Operation(summary = "게임 직업 조회")
    @PostMapping("/class/{gameId}")
    public ResponseEntity<Void> addGameClass(@PathVariable Long gameId,
                                             @RequestBody GameClassReqVo gameClassReqVo) {
        gameDetailsService.addGameClass(gameId, modelMapper.map(gameClassReqVo, GameClassReqDto.class));

        return new ResponseEntity<>(ResponseSuccess.ADD_GAME_CLASS_SUCCESS);
    }

    @DeleteMapping("/class/{classId}")
    public ResponseEntity<Void> deleteGameClass(@PathVariable Long classId) {
        gameDetailsService.deleteGameClass(classId);
        return new ResponseEntity<>(ResponseSuccess.DELETE_GAME_CLASS_SUCCESS);
    }

    @PostMapping("/position/{gameId}")
    public ResponseEntity<Void> addGamePosition(@PathVariable Long gameId,
                                                @RequestBody GamePositionReqVo gamePositionReqVo) {
        gameDetailsService.addGamePosition(gameId, modelMapper.map(gamePositionReqVo, GamePositionReqDto.class));

        return new ResponseEntity<>(ResponseSuccess.ADD_GAME_POSITION_SUCCESS);
    }

    @DeleteMapping("/position/{positionId}")
    public ResponseEntity<Void> deleteGamePosition(@PathVariable Long positionId) {
        gameDetailsService.deleteGamePosition(positionId);
        return new ResponseEntity<>(ResponseSuccess.DELETE_GAME_POSITION_SUCCESS);
    }

    @PostMapping("/server/{gameId}")
    public ResponseEntity<Void> addGameServer(@PathVariable Long gameId,
                                                @RequestBody GameServerReqVo gameServerReqVo) {
        gameDetailsService.addGameServer(gameId, modelMapper.map(gameServerReqVo, GameServerReqDto.class));

        return new ResponseEntity<>(ResponseSuccess.ADD_GAME_SERVER_SUCCESS);
    }

    @DeleteMapping("/server/{serverId}")
    public ResponseEntity<Void> deleteGameServer(@PathVariable Long serverId) {
        gameDetailsService.deleteGameServer(serverId);
        return new ResponseEntity<>(ResponseSuccess.DELETE_GAME_SERVER_SUCCESS);
    }

    @PostMapping("/tier/{gameId}")
    public ResponseEntity<Void> addGameTier(@PathVariable Long gameId,
                                              @RequestBody GameTierReqVo gameTierReqVo) {
        gameDetailsService.addGameTier(gameId, modelMapper.map(gameTierReqVo, GameTierReqDto.class));

        return new ResponseEntity<>(ResponseSuccess.ADD_GAME_TIER_SUCCESS);
    }

    @DeleteMapping("/tier/{tierId}")
    public ResponseEntity<Void> deleteGameTier(@PathVariable Long tierId) {
        gameDetailsService.deleteGameTier(tierId);
        return new ResponseEntity<>(ResponseSuccess.DELETE_GAME_TIER_SUCCESS);
    }
}
