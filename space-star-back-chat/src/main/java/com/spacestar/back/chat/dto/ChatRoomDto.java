package com.spacestar.back.chat.dto;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ChatRoomDto {
    private String roomNumber;
    private String otherMemberUuid;

    public static ChatRoom toEntity(String roomNumber){
        return ChatRoom.builder()
                .roomNumber(roomNumber)
                .build();
    }

    public static ChatRoomDto fromEntity(ChatRoom chatRoom, String excludeUuid) {
        List<String> members = chatRoom.getChatMembers().stream()
                .map(ChatMember::getMemberUuid)
                .toList();
        // 다른 멤버의 UUID를 찾음 (자신의 UUID 제외)
        String otherMemberUuid = members.stream()
                .filter(uuid -> !uuid.equals(excludeUuid))
                .findFirst()
                .orElse(null);

        return new ChatRoomDto(chatRoom.getRoomNumber(), otherMemberUuid);
    }



}
