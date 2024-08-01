package com.spacestar.back.chat.controller;

import com.spacestar.back.chat.dto.ChatRoomNumberDto;
import com.spacestar.back.chat.vo.res.ChatRoomNumberResVo;
import com.spacestar.back.converter.ConvertToIndexVo;
import com.spacestar.back.chat.dto.ChatRoomDetailDto;
import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.dto.ReceiverUuidDto;
import com.spacestar.back.chat.service.ChatRoomService;
import com.spacestar.back.chat.vo.req.ReceiverUuidReqVo;
import com.spacestar.back.chat.vo.res.ChatRoomDetailResVo;
import com.spacestar.back.chat.vo.res.ChatRoomResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/one-to-one")
@Tag(name = "ChatRoom", description = "1:1 채팅방")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ModelMapper mapper;

    // 1:1 채팅방 생성
    @Operation(summary = "1:1 채팅방 생성", description = "상대방과 1:1 채팅방을 생성합니다.")
    @PostMapping("/chatroom/create")
    public ResponseEntity<ChatRoomNumberResVo> addChatRoom(@RequestHeader String uuid, @RequestBody ReceiverUuidReqVo receiverUuid) {
        ReceiverUuidDto receiverUuidDto = mapper.map(receiverUuid, ReceiverUuidDto.class);
        ChatRoomNumberDto chatRoomNumberDto = chatRoomService.addChatRoom(uuid, receiverUuidDto.getReceiverUuid());
        ChatRoomNumberResVo chatRoomNumberResVo = mapper.map(chatRoomNumberDto, ChatRoomNumberResVo.class);
        return new ResponseEntity<>(ResponseSuccess.CREATE_CHATROOM_SUCCESS, chatRoomNumberResVo);
    }

    // 본인이 참여한 (1:1) 채팅방 목록 조회
    @Operation(summary = "채팅방 목록 조회", description = "본인이 참여한 (1:1) 채팅방 목록을 조회합니다.")
    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatRoomResVo>> getChatRoomList(@RequestHeader String uuid) {
        List<ChatRoomDto> chatRoomDtoList = chatRoomService.getChatRoomList(uuid);

        List<ChatRoomResVo> chatRoomResVos = ConvertToIndexVo.convertAndIndex(chatRoomDtoList, ChatRoomResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_LIST_SUCCESS, chatRoomResVos);
    }

    // 채팅방 상세 조회 ( 채팅방 정보 가져오기 )
    @Operation(summary = "채팅방 상세 조회", description = "채팅방 상세 정보를 조회합니다.")
    @GetMapping("/chatroom/{roomNumber}")
    public ResponseEntity<List<ChatRoomDetailResVo>> getChatRoomDetail(@PathVariable String roomNumber) {
        List<ChatRoomDetailDto> chatRoomDetailDtosList = chatRoomService.getChatRoomDetail(roomNumber);

        List<ChatRoomDetailResVo> chatRoomDetailResVos = ConvertToIndexVo.convertAndIndex(
                chatRoomDetailDtosList, ChatRoomDetailResVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_DETAIL_SUCCESS, chatRoomDetailResVos);
    }


}
