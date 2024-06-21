package com.spacestar.back.teamChat.vo.req;

import com.spacestar.back.chat.enums.MessageType;
import lombok.Getter;

@Getter
public class TeamChatMessageReqVo {

    private String senderUuid;
    private String content;
    private MessageType messageType;

}
