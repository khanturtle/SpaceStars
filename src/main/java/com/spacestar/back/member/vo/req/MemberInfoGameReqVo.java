package com.spacestar.back.member.vo.req;

import lombok.Getter;

@Getter
public class MemberInfoGameReqVo {

    private Long gameId;
    private String gameNickname;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
}
