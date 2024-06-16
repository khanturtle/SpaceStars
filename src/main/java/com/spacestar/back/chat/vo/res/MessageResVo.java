package com.spacestar.back.chat.vo.res;

import com.spacestar.back.chat.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessageResVo {
    int index;
    String roomNumber;
    String senderUuid;
    String content;
    MessageType messageType;
    Instant createdAt;
}
