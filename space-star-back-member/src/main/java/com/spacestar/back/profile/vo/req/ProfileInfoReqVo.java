package com.spacestar.back.profile.vo.req;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileInfoReqVo {

    @Pattern(regexp = "^[가-힣a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"|,.<>/?~]{1,30}$", message = "30자 이내로 입력 가능합니다.")
    private String introduction;

    private Long mbtiId;
    private boolean swipe;
}




