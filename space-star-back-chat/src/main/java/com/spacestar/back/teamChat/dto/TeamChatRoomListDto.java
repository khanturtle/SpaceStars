package com.spacestar.back.teamChat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
public class TeamChatRoomListDto {

    private String roomNumber;

    private String roomName;

    @Builder
    public TeamChatRoomListDto(String roomNumber, String roomName) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

}
