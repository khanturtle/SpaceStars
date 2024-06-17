package com.spacestar.back.teamChat.vo.res;

import com.spacestar.back.teamChat.dto.TeamChatRoomDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamChatRoomRecruitReqVo {
    private int index;
    private Long gameId;
    private String roomNumber;
    private String roomName;
    private String memo;

    private List<TeamChatRoomDetailDto> memberUuidList;

    private int currentMembers;
    private int maxMembers;
    private Boolean isFinished;
    private Boolean isPassword;
    private String password;
}
