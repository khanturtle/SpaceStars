package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomService {
    void addChatRoom(String uuid, String receiverUuid);

    List<ChatRoomDto> getChatRoomList(String uuid);
}
