package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    RATE_CREATE_SUCCESS(200, "팀원평가에 성공했습니다."),
    RATE_SKIP_SUCCESS(201, "팀원평가 건너뛰기에 성공했습니다."),

    LEVEL_GET_SUCCESS(200, "레벨 조회에 성공했습니다."),
    LEVEL_INFO_GET_SUCCESS(200, "레벨 상세 정보 조회에 성공했습니다."),
    LEVEL_EXP_GET_SUCCESS(200, "레벨 경험치 조회에 성공했습니다."),

    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다.");


    private final int code;
    private final String message;

}
