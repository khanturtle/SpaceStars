package com.spacestar.back.chat.dto;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomDetailDto {
    private String memberUuid;

    public static ChatRoomDetailDto fromEntity(ChatMember chatMember){
        return ChatRoomDetailDto.builder()
                .memberUuid(chatMember.getMemberUuid())
                .build();
    }
}
