package com.spacestar.back.game.vo;

import com.spacestar.back.game.domain.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameOptionResVo {
    private boolean is_tier;
    private boolean is_position;
    private boolean is_class;
    private boolean is_server;

    public GameOptionResVo(Game game) {
        this.is_tier = game.is_tier();
        this.is_position = game.is_position();
        this.is_class = game.is_class();
        this.is_server = game.is_server();
    }
}
