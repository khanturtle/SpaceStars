package com.spacestar.back.teamChat.controller;

import com.spacestar.back.chat.dto.ChatUserListDto;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.service.TeamChatMessageService;
import com.spacestar.back.teamChat.vo.req.TeamChatExitReqVo;
import com.spacestar.back.teamChat.vo.req.TeamChatJoinReqVo;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "TeamChat", description = "팀 채팅")
public class TeamChatStompController {
    private final TeamChatMessageService teamChatMessageService;
    private final SimpMessagingTemplate messageTemplate;
    private final ConcurrentHashMap<String, List<String>> roomUsers = new ConcurrentHashMap<>();

    @MessageMapping("/team/{roomNumber}")
    public void addTeamChatMessage(@DestinationVariable String roomNumber,
                                   @Payload TeamChatMessageReqVo teamChatMessageReqVo) {
        // VO -> DTO
        TeamMessageDto teamMessageDto = teamChatMessageService.teamMessageToDto(teamChatMessageReqVo, roomNumber);

        // 채팅 메시지 저장
        teamChatMessageService.addTeamChatMessage(teamMessageDto);

        // 구독한 사람들에게 전송
        messageTemplate.convertAndSend("/sub/team/" + roomNumber, teamMessageDto);
    }

    @MessageMapping("/team/join/{roomNumber}")
    public void joinTeamChatRoom(@DestinationVariable String roomNumber,
                                 @Payload TeamChatJoinReqVo teamChatJoinReqVo) {
        String senderUuid = teamChatJoinReqVo.getSenderUuid();

        // 방에 유저 추가
        roomUsers.computeIfAbsent(roomNumber, k -> new ArrayList<>()).add(senderUuid);

        // 입장 메시지 저장
        teamChatMessageService.addTeamChatJoin(roomNumber, senderUuid);

        // 유저 목록 업데이트
        updateRoomUserList(roomNumber);

    }

    @MessageMapping("/team/exit/{roomNumber}")
    public void exitTeamChatRoom(@DestinationVariable String roomNumber,
                                 @Payload TeamChatExitReqVo teamChatExitReqVo) {
        String senderUuid = teamChatExitReqVo.getSenderUuid();

        // 방에서 유저 제거
        roomUsers.computeIfPresent(roomNumber, (k, v) -> v.stream()
                .filter(user -> !user.equals(senderUuid))
                .toList());

        // 퇴장 메시지 저장
        teamChatMessageService.addTeamChatExit(roomNumber, senderUuid);

        // 유저 목록 업데이트
        updateRoomUserList(roomNumber);


    }




    private void updateRoomUserList(String roomNumber) {
        List<String> users = roomUsers.get(roomNumber);
        if (users != null) {
            ChatUserListDto chatUserListDto = new ChatUserListDto(users);
            messageTemplate.convertAndSend("/sub/team/users/" + roomNumber, chatUserListDto);
        }
    }
}




















