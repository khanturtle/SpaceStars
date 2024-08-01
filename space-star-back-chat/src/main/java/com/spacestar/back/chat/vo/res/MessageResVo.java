package com.spacestar.back.chat.vo.res;

import com.spacestar.back.chat.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResVo {
    int index;
    String roomNumber;
    String senderUuid;
    String content;
    MessageType messageType;
    Instant createdAt;
}
