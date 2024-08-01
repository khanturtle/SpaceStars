package com.spacestar.back.teamChat.vo.res;

import com.spacestar.back.teamChat.dto.TeamChatRoomDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamChatRoomRecruitReqVo {
    private int index;
    private Long gameId;
    private String roomNumber;
    private String roomName;
    private String memo;

    private List<TeamChatRoomDetailDto> memberUuidList;

    private int currentMembers;
    private Integer maxMembers;
    private Boolean isFinished;
    private Boolean isPassword;
    private String password;
}
