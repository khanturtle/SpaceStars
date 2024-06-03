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
    private String RoomNumber;

    public static ChatRoom toEntity(String roomNumber){
        return ChatRoom.builder()
                .roomNumber(roomNumber)
                .build();
    }
}
