package com.spacestar.back.auth.vo.req;

import com.spacestar.back.auth.enums.GenderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberInfoReqVo {

    @Size(min = 2, max = 20, message = "닉네임은 2 ~ 20자리까지 가능합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "한글, 영어(소문자, 대문자), 숫자만 입력 가능합니다.")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private LocalDate birth;

    @AssertTrue(message = "약관에 동의해주세요.")
    private boolean infoAgree;
}
