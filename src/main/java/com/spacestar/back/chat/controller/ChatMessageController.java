package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    @RequestMapping("/chat/{roomNumber}")
    public ResponseEntity<List<MessageDto>> getChatMessage(@PathVariable String roomNumber) {
        List<MessageDto> chatMessageVos = chatMessageService.getChatMessage(roomNumber);

        return new ResponseEntity<>(ResponseSuccess.SUCCESS,chatMessageVos);
    }



}

