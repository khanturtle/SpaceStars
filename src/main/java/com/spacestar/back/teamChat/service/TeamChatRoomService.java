package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.dto.TeamChatRoomListDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomRecruitDto;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomReqVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChatRoomService {

    void addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto );

    List<TeamChatRoomListDto> getTeamChatRoomList(String uuid);

    List<TeamChatRoomRecruitDto> getTeamChatRoomRecruitList();
}
