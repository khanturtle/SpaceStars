package com.spacestar.back.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecentMessageCountDto {
    private int unReadMessageCount;

    @Builder
    public RecentMessageCountDto(int UnReadMessageCount) {
        this.unReadMessageCount = UnReadMessageCount;
    }
}
