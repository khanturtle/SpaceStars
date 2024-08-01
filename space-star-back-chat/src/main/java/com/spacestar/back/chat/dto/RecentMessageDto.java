package com.spacestar.back.chat.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class RecentMessageDto {

    private String senderUuid;

    private String lastChatMessage;

    private Instant createdAt;

}
