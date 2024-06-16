package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.chat.vo.req.ChatJoinReqVo;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import io.swagger.v3.oas.annotations.tags.Tag;
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
        messageTemplate.convertAndSend("/room/" + roomNumber, messageDto);

    }
    // 채팅방 입장
    @MessageMapping("/one-to-one/join/{roomNumber}")
    public void joinChatRoom(@DestinationVariable String roomNumber,
                             @Payload ChatJoinReqVo chatJoinReqVo) {
        String senderUuid = chatJoinReqVo.getSenderUuid();
        // 입장 메시지 저장
        chatMessageService.addChatJoin(roomNumber, senderUuid);

        // 입장 메시지 브로드캐스트 (필요에 따라)
        //messageTemplate.convertAndSend("/room/" + roomNumber, joinMessage);
    }

    // 채팅방 퇴장
    @MessageMapping("/one-to-one/exit/{roomNumber}")
    public void exitChatRoom(@DestinationVariable String roomNumber,
                             @Payload ChatJoinReqVo chatExitReqVo) {
        String senderUuid = chatExitReqVo.getSenderUuid();
        // 퇴장 메시지 저장
        chatMessageService.addChatExit(roomNumber, senderUuid);

        // 퇴장 메시지 브로드캐스트 (필요에 따라)

    }
}
