package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.repository.ChatRoomJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomJPARepository chatRoomJPARepository;
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
}
