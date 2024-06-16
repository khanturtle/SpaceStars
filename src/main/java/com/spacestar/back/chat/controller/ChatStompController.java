package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.chat.vo.req.ChatExitReqVo;
import com.spacestar.back.chat.vo.req.ChatJoinReqVo;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Chat", description = "1:1 채팅(전송)")
//@RequestMapping("/chat")
public class ChatStompController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messageTemplate;
    private final ConcurrentHashMap<String, List<String>> roomUsers = new ConcurrentHashMap<>();

    @Autowired
    public ChatStompController(ChatMessageService chatMessageService, SimpMessagingTemplate messageTemplate) {
        this.chatMessageService = chatMessageService;
        this.messageTemplate = messageTemplate;
    }

    // 채팅 메시지 전송

    @MessageMapping("/one-to-one/{roomNumber}")
    public void addChatMessage(
            @DestinationVariable String roomNumber,
            @Payload ChatMessageReqVo chatMessageReqVo) {
        // VO -> DTO
        MessageDto messageDto = chatMessageService.messageToDto(chatMessageReqVo, roomNumber);
        log.info("messageDto: {}", messageDto.getCreatedAt());


        // 채팅 메시지 저장
        chatMessageService.addChatMessage(messageDto);

        // 구독한 사람들에게 전송
        messageTemplate.convertAndSend("/sub/one-to-one/" + roomNumber, messageDto);

    }

    @MessageMapping("/one-to-one/join/{roomNumber}")
    public void joinChatRoom(@DestinationVariable String roomNumber,
                             @Payload ChatJoinReqVo chatJoinReqVo) {
        String senderUuid = chatJoinReqVo.getSenderUuid();

        // 방에 유저 추가
        roomUsers.computeIfAbsent(roomNumber, k -> new ArrayList<>()).add(senderUuid);

        // 입장 메시지 저장
        chatMessageService.addChatJoin(roomNumber, senderUuid);

        // 유저 목록 업데이트
        updateRoomUserList(roomNumber);

        // 입장 메시지 브로드캐스트
//        messageTemplate.convertAndSend("/sub/" + roomNumber, senderUuid + " has joined the room.");
    }

    @MessageMapping("/one-to-one/exit/{roomNumber}")
    public void exitChatRoom(@DestinationVariable String roomNumber,
                             @Payload ChatExitReqVo chatExitReqVo) {
        String senderUuid = chatExitReqVo.getSenderUuid();

        // 방에서 유저 제거
        roomUsers.computeIfPresent(roomNumber, (k, v) -> v.stream()
                .filter(user -> !user.equals(senderUuid))
                .collect(Collectors.toList()));

        // 퇴장 메시지 저장
        chatMessageService.addChatExit(roomNumber, senderUuid);

        // 유저 목록 업데이트
        updateRoomUserList(roomNumber);

        // 퇴장 메시지 브로드캐스트
//        messageTemplate.convertAndSend("/sub/" + roomNumber, senderUuid + " has left the room.");
    }
    private void updateRoomUserList(String roomNumber) {
        List<String> users = roomUsers.get(roomNumber);
        if (users != null) {
            messageTemplate.convertAndSend("/sub/one-to-one/" + roomNumber + "/users", users);
        }
    }
}
