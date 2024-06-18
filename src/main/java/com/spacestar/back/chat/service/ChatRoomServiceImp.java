package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDetailDto;
import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.repository.ChatMemberJPARepository;
import com.spacestar.back.chat.repository.ChatRoomJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomJPARepository chatRoomJPARepository;
    private final ChatMemberJPARepository chatMemberJPARepository;
    private final ChatMemberService chatMemberService;
    private final ChatMessageService chatMessageService;

    @Override
    public void addChatRoom(String uuid, String receiverUuid) {
        String roomNumber = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoomDto.toEntity(roomNumber);
        // 채팅방 생성
        chatRoomJPARepository.save(chatRoom);
        log.info(receiverUuid,"receiverUuid");
        // 채팅방에 참여자 추가
        chatMemberService.addMemberToChatRoom(chatRoom, uuid);
        chatMemberService.addMemberToChatRoom(chatRoom, receiverUuid);
        // 참여자 퇴장시간 추가
        chatMessageService.addChatExit(roomNumber, uuid);
        chatMessageService.addChatExit(roomNumber, receiverUuid);
    }

    // 본인이 참여한 채팅방 목록 조회

    @Override
    public List<ChatRoomDto> getChatRoomList(String uuid) {
        // 중간테이블 조회
        List<ChatMember> chatMemberList = chatMemberJPARepository.findAllByMemberUuid(uuid);
        // 채팅방 목록 가져오기
        List<ChatRoom> chatRoomList = chatMemberList.stream()
                .map(ChatMember::getChatRoom)
                .distinct() // 중복된 채팅방 제거
                .toList();

        // 채팅방 목록을 DTO로 변환
        return chatRoomList.stream()
                .map(chatRoom -> ChatRoomDto.fromEntity(chatRoom, uuid))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatRoomDetailDto> getChatRoomDetail(String roomNumber) {
        ChatRoom chatRoom = chatRoomJPARepository.findByRoomNumber(roomNumber);
        List<ChatMember> chatMemberList = chatMemberJPARepository.findAllByChatRoom(chatRoom);

        return chatMemberList.stream()
                .map(ChatRoomDetailDto::fromEntity)
                .toList();
    }
}
