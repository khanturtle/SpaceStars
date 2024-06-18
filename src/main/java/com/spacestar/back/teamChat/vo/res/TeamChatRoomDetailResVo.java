package com.spacestar.back.teamChat.vo.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamChatRoomDetailResVo {

    private int index;

    private Long gameId;

    private String roomNumber;

    private String roomName;

    private Boolean isPassword;

    private String password;

    private int maxMembers;

    private Boolean isFinished;

    private String memo;
}
