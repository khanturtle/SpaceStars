package com.spacestar.back.auth.vo.req;

import com.spacestar.back.auth.enums.GenderType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberInfoReqVo {

    private String nickname;
    private GenderType gender;
    private LocalDate birth;
    private boolean infoAgree;
}
