package com.spacestar.back.profile.vo.req;

import lombok.Getter;

@Getter
public class ProfilePlayGameInfoReqVo {

    private Long gameId;
    private String gameNickname;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
}
