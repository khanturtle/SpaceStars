package com.spacestar.back.teamChat.vo.req;

import lombok.Getter;

@Getter
public class TeamChatRoomReqVo {

    private String roomName;

    private Boolean isPassword;

    private String password;

    private int maxMembers;

    private Long gameId;

    private String memo;

}
