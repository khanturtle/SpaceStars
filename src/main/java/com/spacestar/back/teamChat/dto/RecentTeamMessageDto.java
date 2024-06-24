package com.spacestar.back.teamChat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class RecentTeamMessageDto {
    private String lastChatMessage;

    private Instant createdAt;

    private int unReadCount;

}
