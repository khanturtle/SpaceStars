package com.spacestar.back.chat.dto;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChatRoomDto {
    private String roomNumber;

    public static ChatRoom toEntity(String roomNumber){
        return ChatRoom.builder()
                .roomNumber(roomNumber)
                .build();
    }

    public static ChatRoomDto fromEntity(ChatRoom chatRoom){
        return new ChatRoomDto(chatRoom.getRoomNumber());
    }



}
