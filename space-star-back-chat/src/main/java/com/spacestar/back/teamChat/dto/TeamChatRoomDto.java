package com.spacestar.back.teamChat.dto;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamChatRoomDto {

    private Long gameId;

    private String roomNumber;

    private String roomName;

    private Boolean isPassword;

    private String password;

    private Integer maxMembers;

    private Boolean isFinished;

    private String memo;

    @Builder


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
    public static TeamChatRoom ToEntity(TeamChatRoomDto teamChatRoomDto,Long id){
        return TeamChatRoom.builder()
                .id(id)
                .gameId(teamChatRoomDto.getGameId())
                .roomNumber(teamChatRoomDto.getRoomNumber())
                .roomName(teamChatRoomDto.getRoomName())
                .isPassword(teamChatRoomDto.getIsPassword())
                .password(teamChatRoomDto.getPassword())
                .maxMembers(teamChatRoomDto.getMaxMembers())
                .isFinished(teamChatRoomDto.getIsFinished())
                .memo(teamChatRoomDto.getMemo())
                .build();
    }
}
