package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.dto.RecentMessageCountDto;
import com.spacestar.back.chat.dto.RecentMessageDto;
import com.spacestar.back.chat.enums.MessageType;
import com.spacestar.back.chat.repository.ChatMessageMongoRepository;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public List<MessageDto> getReadMessage(String uuid, String roomNumber, Pageable pageable) {

        // MongoDB에서 roomNumber와 uuid에 해당하는 사용자의 마지막 퇴장 메시지를 가져오기
        Optional<ChatMessageCollection> optionalExitMessage = chatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);

        log.info("optionalExitMessage: {}", optionalExitMessage);

        if (optionalExitMessage.isPresent()) {
            ChatMessageCollection exitMessage = optionalExitMessage.get();
            Instant exitTime = exitMessage.getExitTime();

            // exitTime 이후의 메시지를 찾기
            Page<ChatMessageCollection> readMessages = chatMessageRepository.findReadMessage(roomNumber, exitTime, pageable);

            log.info(readMessages.toString());
            // MessageDto로 변환하여 반환
            return readMessages.stream()
                    .map(MessageDto::messageDtoFromEntity)
                    .toList();
        }

        // Optional이 empty인 경우, 즉 사용자의 퇴장 기록이 없는 경우, 빈 리스트 반환
        return Collections.emptyList();
    }
    @Override
    public List<MessageDto> getUnreadMessage(String uuid, String roomNumber) {
        // MongoDB에서 roomNumber와 uuid에 해당하는 사용자의 마지막 퇴장 메시지를 가져오기
        Optional<ChatMessageCollection> optionalExitMessage = chatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);

        if (optionalExitMessage.isPresent()) {
            ChatMessageCollection exitMessage = optionalExitMessage.get();
            Instant exitTime = exitMessage.getExitTime();

            // exitTime 이후의 메시지를 찾기
            List<ChatMessageCollection> unreadMessages = chatMessageRepository.findUnreadMessage(roomNumber, exitTime);

            // MessageDto로 변환하여 반환
            return unreadMessages.stream()
                    .map(MessageDto::messageDtoFromEntity)
                    .toList();
        }

        // Optional이 empty인 경우, 즉 사용자의 퇴장 기록이 없는 경우, 빈 리스트 반환
        return Collections.emptyList();
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
    public RecentMessageDto getRecentMessage(String uuid, String roomNumber) {

        List<ChatMessageCollection> OpRecentMessage = chatMessageRepository.findRecentMessage(roomNumber);

        if (OpRecentMessage.isEmpty()) {
            return RecentMessageDto.builder()
                    .senderUuid("")
                    .lastChatMessage("")
                    .createdAt(null)
                    .build();
        }
        ChatMessageCollection recentMessage = OpRecentMessage.get(0);

        // 마지막 메시지가 텍스트면 content, 아니면 사진을 보냈습니다
        String lastChatMessage = (recentMessage.getMessageType() == MessageType.TEXT) ?
                recentMessage.getContent() : "사진을 보냈습니다";

        return RecentMessageDto.builder()
                .senderUuid(recentMessage.getSenderUuid())
                .lastChatMessage(lastChatMessage)
                .createdAt(recentMessage.getCreatedAt())
                .build();
    }

    @Override
    public RecentMessageCountDto getRecentMessageCount(String uuid, String roomNumber) {
        Optional<ChatMessageCollection> optionalExitMessage = chatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);

//        없으면 빈 리스트 반환
        if (optionalExitMessage.isEmpty()) {
            return RecentMessageCountDto.builder()
                    .UnReadMessageCount(0)
                    .build();
        }

        ChatMessageCollection exitMessage = optionalExitMessage.get();
        Instant exitTime = exitMessage.getExitTime();
        List<ChatMessageCollection> unreadMessages = chatMessageRepository.findUnreadMessage(roomNumber, exitTime);


//        999개 이상이면 999까지만
        int unReadCount = Math.min(unreadMessages.size(), 999);
        return RecentMessageCountDto.builder()
                .UnReadMessageCount(unReadCount)
                .build();
    }


}
