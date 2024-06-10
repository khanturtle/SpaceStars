package com.spacestar.back.teamChat.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.service.TeamChatRoomService;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/team")
@Tag(name = "TeamChatRoom", description = "팀 채팅방")
public class TeamChatRoomController {
    private final TeamChatRoomService teamChatRoomService;
    private final ModelMapper mapper;

    // 팀 채팅방 생성
    @Operation(summary = "팀 채팅방 생성", description = "팀 채팅방을 생성합니다.")
    @PostMapping("/chatroom/create")
    public ResponseEntity<Void> addTeamChatRoom(@RequestHeader String uuid,
                                                @RequestBody TeamChatRoomReqVo teamChatRoomReqVo) {
        TeamChatRoomReqDto teamChatRoomReqDto = mapper.map(teamChatRoomReqVo, TeamChatRoomReqDto.class);
        teamChatRoomService.addTeamChatRoom(uuid,teamChatRoomReqDto);
        return new ResponseEntity<>(ResponseSuccess.CREATE_TEAM_CHATROOM_SUCCESS);
    }

}
