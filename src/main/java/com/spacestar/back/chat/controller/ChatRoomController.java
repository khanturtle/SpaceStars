package com.spacestar.back.chat.controller;

import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.service.ChatRoomService;
import com.spacestar.back.chat.vo.res.ChatRoomResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ModelMapper mapper;

    // 1:1 채팅방 생성
    @PostMapping("/chatroom/add")
    public ResponseEntity<Void> addChatRoom(@RequestHeader String uuid, @RequestBody String receiverUuid) {
        chatRoomService.addChatRoom(uuid, receiverUuid);
        return new ResponseEntity<>(ResponseSuccess.CREATE_CHATROOM_SUCCESS);
    }

    // 본인이 참여한 (1:1) 채팅방 목록 조회
    @GetMapping
    public ResponseEntity<List<ChatRoomResVo>> getChatRoomList(@RequestHeader String uuid) {
        List<ChatRoomDto> ChatRoomDtoList = chatRoomService.getChatRoomList(uuid);
        List<ChatRoomResVo> chatRoomResVos = ChatRoomDtoList.stream()
                .map(dto -> mapper.map(dto, ChatRoomResVo.class))
                .toList();
        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_LIST_SUCCESS, chatRoomResVos);
    }

    // 채팅방 상세 조회 ( 채팅방 정보 가져오기 )
    @GetMapping("/chatroom/{roomNumber}")
    public ResponseEntity<?> getChatRoomDetail(@PathVariable String roomNumber) {

        return null;
    }

}
