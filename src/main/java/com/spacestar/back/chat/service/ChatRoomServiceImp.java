package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDto;
import com.spacestar.back.chat.repository.ChatRoomJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {

    private final ChatRoomJPARepository chatRoomJPARepository;
    @Override
    public void addChatRoom(String uuid) {
        String roomNumber = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoomDto.toEntity(roomNumber);
        chatRoomJPARepository.save(chatRoom);
//    chatRoomJPARepository.save(uuid);
    }
}
