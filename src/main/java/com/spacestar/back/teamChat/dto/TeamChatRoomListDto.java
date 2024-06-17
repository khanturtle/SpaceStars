package com.spacestar.back.teamChat.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class TeamChatRoomListDto {

    private String roomNumber;

    private String roomName;

    private String lastChatMessage;

    private Instant createdAt;

    private int unReadCount;
}
