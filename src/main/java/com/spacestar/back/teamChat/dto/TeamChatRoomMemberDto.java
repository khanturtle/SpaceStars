package com.spacestar.back.teamChat.dto;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamChatRoomMemberDto {
    private String memberUuid;
    private Boolean ownerStatus;

    @Builder
    public TeamChatRoomMemberDto(String memberUuid, Boolean ownerStatus) {
        this.memberUuid = memberUuid;
        this.ownerStatus = ownerStatus;
    }

    public static TeamChatRoomMemberDto toDto(TeamChatMember teamChatMember){
        return TeamChatRoomMemberDto.builder()
                .memberUuid(teamChatMember.getMemberUuid())
                .ownerStatus(teamChatMember.getOwnerStatus())
                .build();

    }
}
