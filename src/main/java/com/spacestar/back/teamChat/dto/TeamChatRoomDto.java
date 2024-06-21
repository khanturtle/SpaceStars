package com.spacestar.back.teamChat.dto;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class TeamChatRoomDto {

    private Long gameId;

    private String roomNumber;

    private String roomName;

    private Boolean isPassword;

    private String password;

    private int maxMembers;

    private Boolean isFinished;

    private String memo;



    public static TeamChatRoomDto ToDto(TeamChatRoom teamChatRoom){
        return TeamChatRoomDto.builder()
                .gameId(teamChatRoom.getGameId())
                .roomNumber(teamChatRoom.getRoomNumber())
                .roomName(teamChatRoom.getRoomName())
                .isPassword(teamChatRoom.getIsPassword())
                .password(teamChatRoom.getPassword())
                .maxMembers(teamChatRoom.getMaxMembers())
                .isFinished(teamChatRoom.getIsFinished())
                .memo(teamChatRoom.getMemo())
                .build();
    }
}
