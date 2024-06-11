package com.spacestar.back.teamChat.dto;

import lombok.Getter;

@Getter
public class TeamChatRoomDto {

    private String roomNumber;

    private String roomName;

    private Boolean isPassword;

    private String password;

    private int maxMembers;

    private Boolean isFinished;

    private Long gameId;

    private String memo;

}
