package com.spacestar.back.auth.vo.req;

import com.spacestar.back.auth.enums.GenderType;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberJoinReqVo {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, max = 20, message = "닉네임은 2 ~ 20자리까지 가능합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]*$", message = "한글, 영어(소문자, 대문자), 숫자만 입력 가능합니다.")
    private String nickname;

    private String imageUrl;

    @NotNull(message = "성별을 선택해주세요")
    private GenderType gender;

    @NotNull(message = "생년월일을 입력해주세요.")
    private LocalDate birth;

    @NotNull(message = "약관에 동의해주세요.")
    private boolean infoAgree;

}
