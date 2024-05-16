package com.spacestar.back.game.controller;

import com.spacestar.back.game.service.GameService;
import com.spacestar.back.global.ResponseEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    @GetMapping
    public ResponseEntity<?> getGames(){
        return gameService.getGames();
    }
}
