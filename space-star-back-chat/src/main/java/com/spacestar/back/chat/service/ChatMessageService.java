package com.spacestar.back.chat.service;

import com.spacestar.back.chat.dto.MessageDto;
import com.spacestar.back.chat.dto.RecentMessageCountDto;
import com.spacestar.back.chat.dto.RecentMessageDto;
import com.spacestar.back.chat.vo.req.ChatMessageReqVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatMessageService {

    void addChatMessage(MessageDto messageDto);
    MessageDto messageToDto(ChatMessageReqVo chatMessageReqVo, String roomNumber);
    List<MessageDto> getReadMessage(String uuid, String roomNumber, Pageable pageable);

    void addChatJoin(String roomNumber, String senderUuid);

    void addChatExit(String roomNumber, String senderUuid);

    List<MessageDto> getUnreadMessage(String uuid, String roomNumber);

    RecentMessageDto getRecentMessage(String uuid, String roomNumber);

    RecentMessageCountDto getRecentMessageCount(String uuid, String roomNumber);


}
