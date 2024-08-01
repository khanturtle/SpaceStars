package com.spacestar.back.teamChat.controller;

import com.spacestar.back.chat.dto.RecentMessageCountDto;
import com.spacestar.back.converter.ConvertToIndexVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.teamChat.dto.RecentTeamMessageCountDto;
import com.spacestar.back.teamChat.dto.RecentTeamMessageDto;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.service.TeamChatMessageService;
import com.spacestar.back.teamChat.vo.req.TeamMessageResVo;
import com.spacestar.back.teamChat.vo.res.RecentTeamMessageResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/team")
@Tag(name = "TeamChatMessage", description = "팀 채팅 메시지")
public class TeamChatMessageController {

    private final TeamChatMessageService teamChatMessageService;
    private final ModelMapper mapper;

    @Operation(summary = "읽은 팀 채팅 내역 조회", description = "채팅방의 읽은 팀 채팅 내역을 조회합니다.")
    @GetMapping("/message/{roomNumber}")
    public ResponseEntity<List<TeamMessageResVo>> getReadTeamMessage(@RequestHeader String uuid,
                                                                     @PathVariable String roomNumber,
                                                                     @RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        List<TeamMessageDto> chatMessageVos = teamChatMessageService.getReadTeamMessage(uuid, roomNumber,pageable);
        List<TeamMessageResVo> messageResVos = ConvertToIndexVo.convertAndIndex(chatMessageVos, TeamMessageResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_MESSAGE_LIST_SUCCESS, messageResVos);
    }

    @Operation(summary = "안 읽은 팀 채팅 내역 조회", description = "채팅방의 안 읽은 팀 채팅 내역을 조회합니다.")
    @GetMapping("/message/unread/{roomNumber}")
    public ResponseEntity<List<TeamMessageResVo>> getUnreadTeamMessage(@RequestHeader String uuid,
                                                                      @PathVariable String roomNumber) {
        List<TeamMessageDto> chatMessageVos = teamChatMessageService.getUnreadTeamMessage(uuid, roomNumber);
        List<TeamMessageResVo> messageResVos = ConvertToIndexVo.convertAndIndex(chatMessageVos, TeamMessageResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_UNREAD_MESSAGE_LIST_SUCCESS, messageResVos);
    }

    @Operation(summary = "최근 팀 채팅 내역 조회", description = "채팅방의 최근 팀 채팅 내역을 조회합니다.")
    @GetMapping("/message/recent/{roomNumber}")
    public ResponseEntity<RecentTeamMessageResVo> getRecentTeamMessage(@RequestHeader String uuid,
                                                                @PathVariable String roomNumber) {
        RecentTeamMessageDto recentTeamMessageDto = teamChatMessageService.getRecentTeamMessage(uuid, roomNumber);
        RecentTeamMessageResVo recentTeamMessageResVo = mapper.map(recentTeamMessageDto, RecentTeamMessageResVo.class);

        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_RECENT_MESSAGE_SUCCESS, recentTeamMessageResVo);
    }

    //최근 메시지 개수
    @Operation(summary = "최근 팀 채팅 개수 조회", description = "채팅방의 최근 팀 채팅 개수를 조회합니다.")
    @GetMapping("/message/recent/count/{roomNumber}")
    public ResponseEntity<?> getRecentTeamMessageCount(@RequestHeader String uuid,
                                                             @PathVariable String roomNumber) {
        RecentTeamMessageCountDto recentTeamMessageCount = teamChatMessageService.getRecentMessageCount(uuid, roomNumber);

        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_RECENT_MESSAGE_COUNT_SUCCESS, recentTeamMessageCount);
    }
}
