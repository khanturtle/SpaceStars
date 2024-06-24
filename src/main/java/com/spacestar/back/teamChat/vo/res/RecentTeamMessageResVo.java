package com.spacestar.back.teamChat.vo.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecentTeamMessageResVo {
    private String senderUuid;

    private String lastChatMessage;

    private Instant createdAt;
}
