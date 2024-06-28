package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.dto.TeamChatRoomDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomListDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomMemberDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomNumberDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomRecruitDto;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomReqVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChatRoomService {

    TeamChatRoomNumberDto addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto );

    List<TeamChatRoomListDto> getTeamChatRoomList(String uuid);

    List<TeamChatRoomRecruitDto> getTeamChatRoomRecruitList(Long gameId, Integer recruitNumber, Boolean isRecruitFinish);

    TeamChatRoomDto getTeamChatRoomDetail(String roomNumber);

    void joinTeamChatRoom(String uuid, String roomNumber, String password);
    void exitTeamChatRoom(String uuid, String roomNumber);

    List<TeamChatRoomMemberDto> getTeamChatRoomMembers(String roomNumber);

    void kickTeamChatRoom(String uuid, String roomNumber, String receiverUuid);

    void changeOwnerTeamChatRoom(String uuid, String roomNumber, String receiverUuid);

    void finishRecruit(String uuid, String roomNumber);

    void changeTeamChatRoom(String uuid, String roomNumber, TeamChatRoomReqDto teamChatRoomReqDto);

    boolean isMember(String uuid, String roomNumber);
}
