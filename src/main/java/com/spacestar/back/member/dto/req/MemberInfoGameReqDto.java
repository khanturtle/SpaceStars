package com.spacestar.back.member.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInfoGameReqDto {
    private Long gameId;
    private String gameNickname;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
}
