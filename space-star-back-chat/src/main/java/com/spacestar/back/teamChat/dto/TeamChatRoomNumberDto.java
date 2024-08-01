package com.spacestar.back.teamChat.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamChatRoomNumberDto {
    private String roomNumber;

    @Builder
    public TeamChatRoomNumberDto(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
