package com.spacestar.back.chat.controller;

import com.spacestar.back.chat.service.ChatRoomService;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @PostMapping("/add")
    public ResponseEntity<Void> addChatRoom(@RequestHeader String uuid, @RequestBody String receiverUuid) {
        chatRoomService.addChatRoom(uuid, receiverUuid);
        return new ResponseEntity<>(ResponseSuccess.SUCCESS);
    }



}
