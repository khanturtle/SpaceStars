package com.spacestar.back.member.vo.res;

import com.spacestar.back.member.enums.GenderType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ProfileChattingResVo {

    private String mainProfileImageUrl;
    private String nickname;
    private Long mainGameId;
    private String description;
}
