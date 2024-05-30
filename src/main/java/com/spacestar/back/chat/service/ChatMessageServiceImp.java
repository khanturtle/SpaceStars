package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.ChatMessageCollection;
import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ChatMessageServiceImp implements ChatMessageService{

    private final ChatMessageMongoRepository chatMessageRepository;
    @Autowired
    public ChatMessageServiceImp(ChatMessageMongoRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }


    @Override
    public void addChatMessage(MessageDto messageDto) {
        //9시간 더하기
//        messageDto.setCreatedAt(messageDto.getCreatedAt().plusHours(9));
        // MongoDB에 저장
        chatMessageRepository.save(MessageDto.toEntity(messageDto));
    }

    @Override
    public MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber){
        LocalDateTime createdAt = LocalDateTime.now();
        return MessageDto.chatMessageReqVoToDto(chatMessageReqVo, roomNumber, createdAt);
    }

    @Override
    public List<MessageDto> getChatMessage(String roomNumber) {
        // MongoDB에서 roomNumber에 해당하는 채팅 메시지 가져오기
        List<ChatMessageCollection> chatMessageCollections = chatMessageRepository.findAllByRoomNumber(roomNumber);
        // ChatMessageCollection -> MessageDto stream
        return chatMessageCollections.stream()
                .map(MessageDto::messageDtoFromEntity)
                .toList();
    }

}
