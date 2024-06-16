package com.spacestar.back.teamChat.dto;


import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamChatRoomDetailDto {
    private String memberUuid;



    public static TeamChatRoomDetailDto fromEntity(TeamChatMember teamChatMember){
        return TeamChatRoomDetailDto.builder()
                .memberUuid(teamChatMember.getMemberUuid())
                .build();
    }
}
