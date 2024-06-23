package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    RATE_CREATE_SUCCESS(200, "팀원평가에 성공했습니다."),

    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다.");


    private final int code;
    private final String message;

}
