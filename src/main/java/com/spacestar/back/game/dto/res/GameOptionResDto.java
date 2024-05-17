package com.spacestar.back.game.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameOptionResDto {
    private Boolean is_tier;
    private Boolean is_position;
    private Boolean is_class;
    private Boolean is_server;

    public GameOptionResDto(Boolean is_tier,Boolean is_position,Boolean is_class,Boolean is_server) {
        this.is_tier = is_tier;
        this.is_position = is_position;
        this.is_class = is_class;
        this.is_server = is_server;
    }
}
