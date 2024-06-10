package com.spacestar.back.teamChat.dto.req;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamChatRoomReqDto {
    private String roomName;

    private Boolean isPassword;

    private String password;

    private int maxMembers;

    private Long gameId;

    private String memo;

    public static TeamChatRoom toEntity(TeamChatRoomReqDto teamChatRoomReqDto,String roomNumber ){
        return TeamChatRoom.builder()
                .roomNumber(roomNumber)
                .roomName(teamChatRoomReqDto.getRoomName())
                .isPassword(teamChatRoomReqDto.getIsPassword())
                .password(teamChatRoomReqDto.getPassword())
                .maxMembers(teamChatRoomReqDto.getMaxMembers())
                .isFinished(false)
                .gameId(teamChatRoomReqDto.getGameId())
                .memo(teamChatRoomReqDto.getMemo())
                .build();
    }
}
