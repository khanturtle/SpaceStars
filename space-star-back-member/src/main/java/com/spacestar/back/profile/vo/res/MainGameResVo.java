package com.spacestar.back.profile.vo.res;

import lombok.Getter;

@Getter
public class MainGameResVo {

    private Long gameId;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
    private String gameNickname;
    private boolean main;
}
