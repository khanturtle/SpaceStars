package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GameServer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameServerResDto {
    private int index;
    private long id;
    private String name;
    private String nameKor;
    private String image;
    public static GameServerResDto toGameServerResDto(int index, GameServer gameServer) {
        return GameServerResDto.builder()
                .index(index)
                .id(gameServer.getId())
                .name(gameServer.getGameServerName())
                .image(gameServer.getGameServerImage())
                .nameKor(gameServer.getGameServerNameKor())
                .build();
    }
}
