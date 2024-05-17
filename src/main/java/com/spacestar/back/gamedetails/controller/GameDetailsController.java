package com.spacestar.back.gamedetails.controller;

import com.spacestar.back.gamedetails.dto.res.GameClassResDto;
import com.spacestar.back.gamedetails.service.GameDetailsService;
import com.spacestar.back.gamedetails.vo.GameClassResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
}
