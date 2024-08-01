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
    NOT_MODIFIED_ALARM(6001, "유효하지 않는 알림입니다."),
    NOT_DELETE_ALARM(6002, "유효하지 않는 알림입니다."),
    DUPLICATE_CONNECTION(6003, "이미 연결되었습니다."),
    SSE_MEMBER_NOT_FOUND(6004, "연결된 회원을 찾을 수 없습니다.");


    private final int code;
    private final String message;

}
