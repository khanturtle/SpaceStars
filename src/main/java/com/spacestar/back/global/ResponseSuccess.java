package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),

    SWIPE_ADD_SUCCESS(HttpStatus.OK.value(), "채팅 요청 보내기 성공"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다.");


    private final int code;
    private final String message;

}
