package com.spacestar.back.chat.dto;


import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.enums.teamParticipationType;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMemberDto {
    ChatRoom chatRoom;
    String memberUuid;
    teamParticipationType participationType;


    public static ChatMember toEntity(ChatRoom chatRoom, String memberUuid, teamParticipationType participationType){
        return ChatMember.builder()
                .chatRoom(chatRoom)
                .memberUuid(memberUuid)
                .particpationType(participationType)
                .build();
    }


}
