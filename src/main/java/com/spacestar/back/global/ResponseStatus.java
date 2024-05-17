package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(200, "요청에 성공했습니다."),

    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    /**
     * 2000: member
     **/
    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다."),
    DUPLICATED_MEMBERS(2003, "이미 가입된 회원입니다." ),
    BLACKLIST_MEMBER(2004,"영구 탈퇴된 회원입니다."),
    DUPLICATED_NICKNAME(2005, "중복된 닉네임입니다.");
//    TOKEN_NULL(false, 2003, "토큰이 존재하지 않습니다."),

    private final int code;
    private final String message;

}
