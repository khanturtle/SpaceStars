package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),

    MESSAGE_CREATE_SUCCESS(201, "메시지 생성 성공"),

    INTERNAL_SERVER_ERROR(900, "Internal server error");


    private final int code;
    private final String message;

}
