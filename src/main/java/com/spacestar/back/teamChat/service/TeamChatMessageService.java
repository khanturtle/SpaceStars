package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.dto.RecentTeamMessageDto;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TeamChatMessageService {

    void addTeamChatMessage(TeamMessageDto teamMessageDto);
    TeamMessageDto teamMessageToDto(TeamChatMessageReqVo teamChatMessageReqVo, String roomNumber);


    void addTeamChatJoin(String roomNumber, String senderUuid);

    void addTeamChatExit(String roomNumber, String senderUuid);

    List<TeamMessageDto> getReadTeamMessage(String uuid, String roomNumber, Pageable pageable);
//    List<TeamMessageDto> getReadTeamMessage(String uuid, String roomNumber);
//    List<TeamMessageDto> getUnreadTeamMessage(String uuid, String roomNumber);
//
//    RecentTeamMessageDto getRecentTeamMessage(String uuid, String roomNumber);
//
}
