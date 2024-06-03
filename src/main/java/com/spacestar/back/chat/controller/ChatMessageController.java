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
@RequestMapping("/api/v1/message")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    private final ModelMapper mapper;

    @GetMapping("/chat/{roomNumber}")
    public ResponseEntity<List<MessageResVo>> getChatMessage(@PathVariable String roomNumber) {
        List<MessageDto> chatMessageVos = chatMessageService.getChatMessage(roomNumber);

        List<MessageResVo> messageResVos = chatMessageVos.stream()
                .map(dto -> mapper.map(dto, MessageResVo.class))
                .toList();
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, messageResVos);
    }



}

