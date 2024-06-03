package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatMemberDto;
import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.repository.ChatMemberJPARepository;
import com.spacestar.back.chat.repository.ChatRoomJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomJPARepository chatRoomJPARepository;
    private final ChatMemberJPARepository chatMemberJPARepository;
    private final ChatMemberService chatMemberService;

    @Override
    public void addChatRoom(String uuid, String receiverUuid) {
        String roomNumber = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoomDto.toEntity(roomNumber);
        // 채팅방 생성
        chatRoomJPARepository.save(chatRoom);

        // 채팅방에 참여자 추가
        chatMemberService.addMemberToChatRoom(chatRoom, uuid);
        chatMemberService.addMemberToChatRoom(chatRoom, receiverUuid);

    }

    // 본인이 참여한 채팅방 목록 조회

    @Override
    public List<ChatRoomDto> getChatRoomList(String uuid) {
        // 중간테이블 조회
        List<ChatMember> chatMemberList = chatMemberJPARepository.findAllByMemberUuid(uuid);
        // 채팅방 목록 가져오기
        List<ChatRoom> chatRoomList = chatMemberList.stream()
                .map(ChatMember::getChatRoom)
                .toList();
        return chatRoomList.stream()
                .map(ChatRoomDto::fromEntity)
                .toList();
    }
}
