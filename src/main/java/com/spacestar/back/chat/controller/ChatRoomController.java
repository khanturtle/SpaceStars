package com.spacestar.back.chat.controller;

import com.spacestar.back.chat.service.ChatRoomService;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/add")
    public ResponseEntity<Void> addChatRoom(@RequestHeader String uuid) {
        chatRoomService.addChatRoom(uuid);
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, null);
    }

    public ResponseEntity<Void> addMatchedChatRoom(@RequestHeader String uuid) {

        return new ResponseEntity<>(ResponseSuccess.SUCCESS, null);
    }

}
