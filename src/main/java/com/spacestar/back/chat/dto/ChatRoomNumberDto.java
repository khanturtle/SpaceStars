package com.spacestar.back.chat.dto;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomNumberDto {
    private String roomNumber;

    @Builder
    public ChatRoomNumberDto(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    //fromEntity
    public static ChatRoomNumberDto fromEntity(ChatRoom chatRoom){
        return ChatRoomNumberDto.builder()
                .roomNumber(chatRoom.getRoomNumber())
                .build();
    }

}
