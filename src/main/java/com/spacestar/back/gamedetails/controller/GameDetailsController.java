package com.spacestar.back.gamedetails.controller;

import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.dto.res.GamePositionResDto;
import com.spacestar.back.gamedetails.dto.res.GameServerResDto;
import com.spacestar.back.gamedetails.service.GameDetailsService;
import com.spacestar.back.gamedetails.vo.res.GameClassResVo;
import com.spacestar.back.gamedetails.vo.res.GamePositionResVo;
import com.spacestar.back.gamedetails.vo.res.GameServerResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameDetailsController {
    private final ModelMapper modelMapper;
    private final GameDetailsService gameDetailsService;
    @GetMapping("/class/{gameId}")
    public ResponseEntity<List<GameClassResVo>> getGameClass(
            @PathVariable Long gameId){
        List<GameClassResDto> gameClassResDtos = gameDetailsService.getGameClass(gameId);

        List<GameClassResVo> gameClassResVos = gameClassResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameClassResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_CLASS_SUCCESS,gameClassResVos);
    }

    @GetMapping("/position/{gameId}")
    public ResponseEntity<List<GamePositionResVo>> getGamePosition(
            @PathVariable Long gameId){
        List<GamePositionResDto> gamePositionResDtos = gameDetailsService.getGamePosition(gameId);

        List<GamePositionResVo> gamePositionResVos = gamePositionResDtos.stream()
                .map(dto -> modelMapper.map(dto, GamePositionResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_POSITION_SUCCESS,gamePositionResVos);
    }

    @GetMapping("/server/{gameId}")
    public ResponseEntity<List<GameServerResVo>> getGameServer(
            @PathVariable Long gameId){
        List<GameServerResDto> gameServerResDtos = gameDetailsService.getGameServer(gameId);

        List<GameServerResVo> gameServerResVos = gameServerResDtos.stream()
                .map(dto -> modelMapper.map(dto, GameServerResVo.class))
                .toList();

        return new ResponseEntity<>(
                ResponseSuccess.GET_GAME_SERVER_SUCCESS,gameServerResVos);
    }

}
