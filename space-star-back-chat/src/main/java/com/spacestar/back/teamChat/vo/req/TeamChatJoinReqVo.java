package com.spacestar.back.teamChat.vo.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TeamChatJoinReqVo {

    @NotBlank(message = "유저 UUID를 입력해주세요.")
    private String senderUuid;
}
