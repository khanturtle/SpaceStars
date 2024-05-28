package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;

public interface ChatMessageService {

    void addChatMessage(MessageDto messageDto);
    MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber);
}
