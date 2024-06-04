package com.spacestar.back.auth.vo.res;

import com.spacestar.back.auth.enums.GenderType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberLoginResVo {

    private String accessToken;
    private String uuid;
    private String email;
    private String nickname;
    private LocalDate birth;
    private GenderType gender;
    private boolean infoAgree;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
