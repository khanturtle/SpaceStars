package com.spacestar.back.chat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentMessageResVo {

    private String senderUuid;

    private String lastChatMessage;

    private Instant createdAt;
}
