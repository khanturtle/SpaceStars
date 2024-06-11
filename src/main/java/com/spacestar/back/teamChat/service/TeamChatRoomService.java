package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomReqVo;

public interface TeamChatRoomService {

    void addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto );
}
