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

    NOT_EXIST_ALARM(6000,"존재하지 않는 알림입니다." ),
    NOT_MODIFIED_ALARM(6001, "유효하지 않는 알림입니다.");

    private final int code;
    private final String message;

}
