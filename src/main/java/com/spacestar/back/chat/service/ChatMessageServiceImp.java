package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImp implements ChatMessageService {

    private final ChatMessageMongoRepository chatMessageRepository;


    @Override
    public void addChatMessage(MessageDto messageDto) {
        //9시간 더하기
//        messageDto.setCreatedAt(messageDto.getCreatedAt().plusHours(9));
        // MongoDB에 저장
        chatMessageRepository.save(MessageDto.toEntity(messageDto));
    }

    @Override
    public MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber) {

//        LocalDateTime createdAt = LocalDateTime.now();
        Instant createdAt = Instant.now();
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

    @Override
    public void addChatJoin(String roomNumber, String senderUuid) {
        Instant enterTime = Instant.now();
        chatMessageRepository.save(ChatMessageCollection.builder()
                .roomNumber(roomNumber)
                .senderUuid(senderUuid)
                .enterTime(enterTime)
                .build());
    }

    @Override
    public void addChatExit(String roomNumber, String senderUuid) {
        Instant exitTime = Instant.now();
        chatMessageRepository.save(ChatMessageCollection.builder()
                .roomNumber(roomNumber)
                .senderUuid(senderUuid)
                .exitTime(exitTime)
                .build());
    }

    @Override
    public List<MessageDto> getUnreadMessage(String uuid, String roomNumber) {
        // MongoDB에서 roomNumber에 해당하는 채팅 메시지 가져오기
        ChatMessageCollection exitMessage = chatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);
        if (exitMessage != null) {
            Instant exitTime = exitMessage.getExitTime();
            // exitTime 이후의 메시지를 찾기.
            List<ChatMessageCollection> unreadMessages = chatMessageRepository.findUnreadMessage(roomNumber, exitTime);

            // MessageDto로 변환하여 반환.
            return unreadMessages.stream()
                    .map(MessageDto::messageDtoFromEntity)
                    .toList();
        }
        return List.of();
        // exitTime이 없으면 모든 메시지를 가져옵니다.

        // ChatMessageCollection -> MessageDto stream
//        return chatMessageCollections.stream()
//                .map(MessageDto::messageDtoFromEntity)
//                .toList();
    }

}
