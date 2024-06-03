package com.spacestar.back.chat.dto;


import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.enums.ParticpationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatMemberDto {
    ChatRoom chatRoom;
    String memberUuid;
    ParticpationType particpationType;


    public static ChatMember toEntity(ChatRoom chatRoom, String memberUuid, ParticpationType particpationType){
        return ChatMember.builder()
                .chatRoom(chatRoom)
                .memberUuid(memberUuid)
                .particpationType(particpationType)
                .build();
    }
}
