package com.spacestar.back.member.vo.res;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NicknameResVo {

    private boolean duplicated;
    private String message;


    public NicknameResVo(boolean duplicated, String message) {
        this.duplicated = duplicated;
        this.message = message;
    }
}
