package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),
    INTERNAL_SERVER_ERROR(900, "Internal server error"),
    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다."),

    //mbti
    MBTI_LIST_SUCCESS(200, "MBTI 리스트 조회 성공"),
    MBTI_NAME_SELECT_SUCCESS(200,"MBTI 이름 조회 성공" );


    private final int code;
    private final String message;

}
