package com.spacestar.back.chat.vo.res;

import com.spacestar.back.chat.enums.MessageType;
import lombok.Getter;

import java.time.Instant;

@Getter
public class MessageResVo {
    String roomNumber;
    String senderUuid;
    String content;
    MessageType messageType;
    Instant createdAt;
}
