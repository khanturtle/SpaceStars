package com.spacestar.back.teamChat.vo.req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TeamChatRoomReqVo {

    @NotBlank(message = "방 이름을 입력해주세요.")
    @Size(min = 1, max = 20, message = "방 이름은 1자 이상 20자 이하로 입력해주세요.")
    private String roomName;

    @NotNull(message = "비밀번호 여부를 선택해주세요.")
    private Boolean isPassword;

    private String password;

    @Min(value = 2, message = "최소 인원은 2명입니다.")
    private int maxMembers;

    @NotBlank(message = "게임 아이디를 입력해주세요.")
    private Long gameId;

    private String memo;

}
