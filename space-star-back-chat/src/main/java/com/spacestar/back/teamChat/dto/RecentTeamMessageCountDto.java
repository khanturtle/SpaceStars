package com.spacestar.back.teamChat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecentTeamMessageCountDto {
    private int unReadMessageCount;

    @Builder
    public RecentTeamMessageCountDto(int UnReadMessageCount) {
        this.unReadMessageCount = UnReadMessageCount;
    }
}
