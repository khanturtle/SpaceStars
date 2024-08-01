package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

    /**
     * 200: 요청 성공
     **/
    SUCCESS(200, "요청에 성공했습니다."),

    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다."),
    SWIPE_ALREADY_EXIST(HttpStatus.FORBIDDEN.value(), "이미 요청을 보냈습니다."),
    WAITING_MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND.value(), "대기큐에 없는 사용자입니다.");
//    TOKEN_NULL(false, 2003, "토큰이 존재하지 않습니다."),


    private final int code;
    private final String message;

}
