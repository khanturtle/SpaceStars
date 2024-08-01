package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.ChatRoomDetailDto;
import com.spacestar.back.chat.dto.ChatRoomDto;

import com.spacestar.back.chat.dto.ChatRoomNumberDto;
import java.util.List;

public interface ChatRoomService {
    ChatRoomNumberDto addChatRoom(String uuid, String receiverUuid);

    List<ChatRoomDto> getChatRoomList(String uuid);

    List<ChatRoomDetailDto> getChatRoomDetail(String roomNumber);
}
