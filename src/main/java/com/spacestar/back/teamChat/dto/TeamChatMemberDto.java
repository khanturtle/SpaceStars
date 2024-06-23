package com.spacestar.back.teamChat.dto;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamChatMemberDto {

    private TeamChatRoom teamChatRoom;

    private String memberUuid;

    private Boolean ownerStatus;

    private TeamParticipationType teamParticipationType;

    public static TeamChatMember toEntity(TeamChatRoom teamChatRoom, String memberUuid, Boolean ownerStatus){
        return TeamChatMember.builder()
                .teamChatRoom(teamChatRoom)
                .memberUuid(memberUuid)
                .ownerStatus(ownerStatus)
                .teamParticipationType(TeamParticipationType.JOINED)
                .build();

    }


    public static TeamChatMember toEntity(TeamChatMemberDto teamChatMemberDto,Long id){
        return TeamChatMember.builder()
                .id(id)
                .teamChatRoom(teamChatMemberDto.getTeamChatRoom())
                .memberUuid(teamChatMemberDto.getMemberUuid())
                .ownerStatus(teamChatMemberDto.getOwnerStatus())
                .teamParticipationType(teamChatMemberDto.getTeamParticipationType())
                .build();

    }


    public static TeamChatMemberDto toDto(TeamChatMember teamChatMember){
        return TeamChatMemberDto.builder()
                .teamChatRoom(teamChatMember.getTeamChatRoom())
                .memberUuid(teamChatMember.getMemberUuid())
                .ownerStatus(teamChatMember.getOwnerStatus())
                .teamParticipationType(teamChatMember.getTeamParticipationType())
                .build();

    }
}
