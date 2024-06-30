package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.ChatMemberDto;
import com.spacestar.back.chat.dto.RecentMessageCountDto;
import com.spacestar.back.converter.ConvertToIndexVo;
import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.dto.RecentMessageDto;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.chat.vo.res.MessageResVo;
import com.spacestar.back.chat.vo.res.RecentMessageResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
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
@RequestMapping("/api/v1/chat/one-to-one")
@Tag(name = "ChatMessage", description = "1:1 채팅 메시지")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ModelMapper mapper;


    // 전체 채팅 내역 조회 (페이지 네이션)
    @Operation(summary = "읽은 채팅 내역 조회", description = "채팅방의 읽은 채팅 내역을 조회합니다.")
    @GetMapping("/message/{roomNumber}")
    public ResponseEntity<List<MessageResVo>> getReadMessage(@RequestHeader String uuid,
                                                             @PathVariable String roomNumber,
                                                             @RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        log.info(pageable.toString());
        List<MessageDto> chatMessageVos = chatMessageService.getReadMessage(uuid, roomNumber,pageable);
        List<MessageResVo> messageResVos = ConvertToIndexVo.convertAndIndex(chatMessageVos, MessageResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_MESSAGE_LIST_SUCCESS, messageResVos);
    }

    // 안 읽은 메시지 내역 조회
    @Operation(summary = "안 읽은 메시지 내역 조회", description = "채팅방의 안 읽은 메시지 내역을 조회합니다.")
    @GetMapping("/message/unread/{roomNumber}")
    public ResponseEntity<List<MessageResVo>> getUnreadMessage(@RequestHeader String uuid,
                                                               @PathVariable String roomNumber) {
        List<MessageDto> chatMessageVos = chatMessageService.getUnreadMessage(uuid, roomNumber);
        List<MessageResVo> messageResVos = ConvertToIndexVo.convertAndIndex(chatMessageVos, MessageResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_UNREAD_MESSAGE_LIST_SUCCESS, messageResVos);
    }

    // 최근 메시지 조회
    @Operation(summary = "최근 메시지 내용 조회", description = "채팅방의 최근 메시지 내용을 조회합니다.(sender, 내용, 시간)")
    @GetMapping("/message/recent/{roomNumber}")
    public ResponseEntity<RecentMessageResVo> getRecentMessage(@RequestHeader String uuid,
                                                               @PathVariable String roomNumber) {
        RecentMessageDto recentMessageDto = chatMessageService.getRecentMessage(uuid, roomNumber);
        RecentMessageResVo recentMessageResVo = mapper.map(recentMessageDto, RecentMessageResVo.class);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_RECENT_MESSAGE_SUCCESS, recentMessageResVo);
    }

    @Operation(summary = "최근 메시지 개수 조회", description = "채팅방의 최근 메시지 개수를 조회합니다.")
    @GetMapping("/message/recent/count/{roomNumber}")
    public ResponseEntity<RecentMessageCountDto> getRecentMessageCount(@RequestHeader String uuid,
                                                         @PathVariable String roomNumber) {
        RecentMessageCountDto recentMessageCount = chatMessageService.getRecentMessageCount(uuid, roomNumber);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_RECENT_MESSAGE_COUNT_SUCCESS, recentMessageCount);
    }

    //테스트

}

