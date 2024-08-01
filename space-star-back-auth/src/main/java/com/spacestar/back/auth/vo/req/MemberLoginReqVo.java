package com.spacestar.back.auth.vo.req;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class MemberLoginReqVo {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
