package com.spacestar.back.gamedetails.controller;

import com.spacestar.back.gamedetails.service.GameDetailsService;
import com.spacestar.back.gamedetails.vo.res.GameOptionResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Game Options", description = "게임 옵션 정보")
@RestController
@RequestMapping("/api/v1/game/option")
@RequiredArgsConstructor
public class GameOptionController {
    private final GameDetailsService gameDetailsService;
    private final ModelMapper mapper;

    @Operation(summary = "게임 직업 정보 조회")
    @GetMapping("/class/{optionId}")
    public ResponseEntity<GameOptionResVo> getGameClass(@PathVariable Long optionId) {

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS,
                mapper.map(gameDetailsService.getGameClassDetail(optionId), GameOptionResVo.class));
    }
    @Operation(summary = "게임 포지션 정보 조회")
    @GetMapping("/position/{optionId}")
    public ResponseEntity<GameOptionResVo> getGamePosition(@PathVariable Long optionId) {

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS,
                mapper.map(gameDetailsService.getGamePositionDetail(optionId), GameOptionResVo.class));
    }
    @Operation(summary = "게임 서버 정보 조회")
    @GetMapping("/server/{optionId}")
    public ResponseEntity<GameOptionResVo> getGameServer(@PathVariable Long optionId) {

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS,
                mapper.map(gameDetailsService.getGameServerDetail(optionId), GameOptionResVo.class));
    }
    @Operation(summary = "게임 티어 정보 조회")
    @GetMapping("/tier/{optionId}")
    public ResponseEntity<GameOptionResVo> getGameTier(@PathVariable Long optionId) {

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS,
                mapper.map(gameDetailsService.getGameTierDetail(optionId), GameOptionResVo.class));
    }
}
