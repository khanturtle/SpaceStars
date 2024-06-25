package com.spacestar.back.teamChat.dto;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TeamChatRoomRecruitDto {
    private Long gameId;
    private String roomNumber;
    private String roomName;
    private String memo;

    private List<TeamChatRoomDetailDto> memberUuidList;

    private int currentMembers;
    private Integer maxMembers;
    private Boolean isFinished;
    private Boolean isPassword;
    private String password;



    public static TeamChatRoomRecruitDto fromEntities(TeamChatRoom teamChatRoom, List<TeamChatRoomDetailDto> memberUuidList) {
        return TeamChatRoomRecruitDto.builder()
                .gameId(teamChatRoom.getGameId())
                .roomNumber(teamChatRoom.getRoomNumber())
                .roomName(teamChatRoom.getRoomName())
                .memo(teamChatRoom.getMemo())
                .memberUuidList(memberUuidList)
                .currentMembers(memberUuidList.size())
                .maxMembers(teamChatRoom.getMaxMembers())
                .isFinished(teamChatRoom.getIsFinished())
                .isPassword(teamChatRoom.getIsPassword())
                .password(teamChatRoom.getPassword())
                .build();
    }
}
