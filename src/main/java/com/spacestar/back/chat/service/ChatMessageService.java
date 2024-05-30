package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;

import java.util.List;

public interface ChatMessageService {

    void addChatMessage(MessageDto messageDto);
    MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber);
    List<MessageDto> getChatMessage(String roomNumber);

}
