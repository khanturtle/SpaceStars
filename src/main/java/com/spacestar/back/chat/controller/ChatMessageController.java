package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.chat.vo.res.MessageResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ModelMapper mapper;


    // 전체 채팅 내역 조회 (페이지 네이션)
    @GetMapping("/message/{roomNumber}")
    public ResponseEntity<List<MessageResVo>> getChatMessage(@PathVariable String roomNumber) {
        List<MessageDto> chatMessageVos = chatMessageService.getChatMessage(roomNumber);

        List<MessageResVo> messageResVos = chatMessageVos.stream()
                .map(dto -> mapper.map(dto, MessageResVo.class))
                .toList();
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, messageResVos);
    }

    // 안 읽은 메시지 내역 조회
    @GetMapping("/message/unread/{roomNumber}")
    public ResponseEntity<List<MessageResVo>> getUnreadMessage(@RequestHeader String uuid,
                                                               @PathVariable String roomNumber) {
        List<MessageDto> chatMessageVos = chatMessageService.getUnreadMessage(uuid,roomNumber);

        List<MessageResVo> messageResVos = chatMessageVos.stream()
                .map(dto -> mapper.map(dto, MessageResVo.class))
                .toList();
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, messageResVos);
    }


}

