package com.spacestar.back.chat.controller;

import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.dto.ReceiverUuidDto;
import com.spacestar.back.chat.service.ChatRoomService;
import com.spacestar.back.chat.vo.req.ReceiverUuidReqVo;
import com.spacestar.back.chat.vo.res.ChatRoomResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@Tag(name = "ChatRoom", description = "채팅방")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ModelMapper mapper;

    // 1:1 채팅방 생성
    @Operation(summary = "1:1 채팅방 생성", description = "상대방과 1:1 채팅방을 생성합니다.")
    @PostMapping("/chatroom/create")
    public ResponseEntity<Void> addChatRoom(@RequestHeader String uuid, @RequestBody ReceiverUuidReqVo receiverUuid){
        ReceiverUuidDto receiverUuidDto = mapper.map(receiverUuid, ReceiverUuidDto.class);
        chatRoomService.addChatRoom(uuid, receiverUuidDto.getReceiverUuid());
        return new ResponseEntity<>(ResponseSuccess.CREATE_CHATROOM_SUCCESS);
    }

    // 본인이 참여한 (1:1) 채팅방 목록 조회
    @Operation(summary = "채팅방 목록 조회", description = "본인이 참여한 (1:1) 채팅방 목록을 조회합니다.")
    @GetMapping("/chatroom")
    public ResponseEntity<List<ChatRoomResVo>> getChatRoomList(@RequestHeader String uuid) {
        List<ChatRoomDto> chatRoomDtoList = chatRoomService.getChatRoomList(uuid);

        List<ChatRoomResVo> chatRoomResVos = IntStream.range(0, chatRoomDtoList.size())
                .mapToObj(i -> {
                    ChatRoomDto dto = chatRoomDtoList.get(i);
                    ChatRoomResVo resVo = mapper.map(dto, ChatRoomResVo.class);
                    resVo.setIndex(i + 1); // 인덱스를 1부터 시작하도록 설정
                    return resVo;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_LIST_SUCCESS, chatRoomResVos);
    }

    // 채팅방 상세 조회 ( 채팅방 정보 가져오기 )
    @Operation(summary = "채팅방 상세 조회", description = "채팅방 상세 정보를 조회합니다.")
    @GetMapping("/chatroom/{roomNumber}")
    public ResponseEntity<?> getChatRoomDetail(@PathVariable String roomNumber) {

        return null;
    }

}
