package com.spacestar.back.profile.vo.req;

import lombok.Getter;

@Getter
public class PlayGameInfoReqVo {

    private Long gameId;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
    private String gameNickname;
    private boolean main;
}
