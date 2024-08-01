package com.spacestar.back.teamChat.vo.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamChatIsMember {
    private Boolean memberStatus;

    @Builder
    public TeamChatIsMember(Boolean memberStatus) {
        this.memberStatus = memberStatus;
    }
}
