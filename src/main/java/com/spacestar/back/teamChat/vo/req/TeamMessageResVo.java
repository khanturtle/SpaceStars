package com.spacestar.back.teamChat.vo.req;

import com.spacestar.back.teamChat.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMessageResVo {
    int index;
    String roomNumber;
    String senderUuid;
    String content;
    MessageType messageType;
    Instant createdAt;
}
