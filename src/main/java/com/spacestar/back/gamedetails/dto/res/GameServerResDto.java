package com.spacestar.back.gamedetails.dto.res;

import com.spacestar.back.gamedetails.domain.GamePosition;
import com.spacestar.back.gamedetails.domain.GameServer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameServerResDto {
    private int index;
    private long gameServerId;
    private String gameServerName;
    private String gameServerNameKor;
    private String gameServerImage;
    public static GameServerResDto toGameServerResDto(int index, GameServer gameServer) {
        return GameServerResDto.builder()
                .index(index)
                .gameServerId(gameServer.getId())
                .gameServerName(gameServer.getGameServerName())
                .gameServerImage(gameServer.getGameServerImage())
                .gameServerNameKor(gameServer.getGameServerNameKor())
                .build();
    }
}
