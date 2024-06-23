package com.spacestar.back.teamChat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamChatRoomMemberResVo {
    private int index;
    private String memberUuid;
    private Boolean ownerStatus;

    @Builder
    public TeamChatRoomMemberResVo(String memberUuid, Boolean ownerStatus) {
        this.memberUuid = memberUuid;
        this.ownerStatus = ownerStatus;
    }
}
