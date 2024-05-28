package com.spacestar.back.chat.vo.req;


import com.spacestar.back.chat.enums.MessageType;
import lombok.Getter;

@Getter
public class ChatMessageReqVo {

    private String senderUuid;
    private String content;
    private MessageType messageType;


}
