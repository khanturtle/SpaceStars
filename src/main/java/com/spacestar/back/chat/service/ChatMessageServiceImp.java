package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatMessageServiceImp implements ChatMessageService{

    private final ChatMessageMongoRepository chatMessageRepository;
    @Autowired
    public ChatMessageServiceImp(ChatMessageMongoRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }


    @Override
    public void addChatMessage(MessageDto messageDto) {
        // MongoDB에 저장
        chatMessageRepository.save(MessageDto.toEntity(messageDto));
    }

    @Override
    public MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber){
        Date createdAt = new Date();
        return MessageDto.toDto(chatMessageReqVo, roomNumber, createdAt);
    }
}
