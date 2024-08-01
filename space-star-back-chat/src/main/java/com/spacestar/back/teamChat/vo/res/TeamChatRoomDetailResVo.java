package com.spacestar.back.teamChat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
