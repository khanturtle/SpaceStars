package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatMemberDto;
import com.spacestar.back.chat.enums.ParticipationType;
import com.spacestar.back.chat.repository.ChatMemberJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMemberServiceImp implements ChatMemberService{
    private final ChatMemberJPARepository chatMemberJPARepository;

    @Override
    public void addMemberToChatRoom(ChatRoom chatRoom, String memberUuid) {
        ChatMember chatMember = ChatMemberDto.toEntity(chatRoom, memberUuid, ParticipationType.JOINED);
        chatMemberJPARepository.save(chatMember);
    }
}
