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
     * 2000: mbti
     **/
    NOT_EXIST_MBTI(2000,"존재하지 않는 mbti입니다." );

    private final int code;
    private final String message;

}
