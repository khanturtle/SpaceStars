package com.spacestar.back.chat.controller;


import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.service.ChatMessageService;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Chat", description = "채팅")
//@RequestMapping("/chat")
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messageTemplate;

    @Autowired
    public ChatController(ChatMessageService chatMessageService, SimpMessagingTemplate messageTemplate) {
        this.chatMessageService = chatMessageService;
        this.messageTemplate = messageTemplate;
    }
    @MessageMapping("/{roomNumber}")
    public void addChatMessage(@DestinationVariable String roomNumber,
                                               @Payload ChatMessageReqVo chatMessageReqVo){
        // VO -> DTO
        MessageDto messageDto = chatMessageService.messageToDto(chatMessageReqVo, roomNumber);
        log.info("messageDto: {}", messageDto.getCreatedAt());
        // 채팅 메시지 저장
        chatMessageService.addChatMessage(messageDto);

        // 구독한 사람들에게 전송
        messageTemplate.convertAndSend("/room/" + roomNumber, messageDto);
    }

}
