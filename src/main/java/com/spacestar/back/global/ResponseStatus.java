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
     * 3000: member
     **/
    NOT_EXIST_PROFILE(3000,"존재하지 않는 프로필입니다." ),










    /**
     * 5000: block
     **/
    ALREADY_BLOCKED(5000, "이미 차단된 사용자입니다.");


    private final int code;
    private final String message;

}
