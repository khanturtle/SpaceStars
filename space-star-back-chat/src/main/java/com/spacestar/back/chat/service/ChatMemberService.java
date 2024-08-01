package com.spacestar.back.chat.service;

import com.spacestar.back.chat.domain.entity.ChatRoom;

public interface ChatMemberService {
    void addMemberToChatRoom(ChatRoom chatRoom, String memberUuid);
}
