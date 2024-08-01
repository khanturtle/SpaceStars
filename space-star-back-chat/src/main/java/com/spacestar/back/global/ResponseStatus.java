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

    NO_EXIST_EXITTIME(301, "나간 시간이 존재하지 않습니다."),



    FULL_MEMBER(401, "인원이 꽉 찼습니다."),
    WRONG_PASSWORD(402, "비밀번호가 틀렸습니다."),
    BANNED_MEMBER(403, "참여가 제한된 방입니다."),
    NOT_OWNER(404, "방장이 아닙니다."),
    ALREADY_JOINED_MEMBER(405, "이미 참여한 방입니다."),
    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다.");

//    TOKEN_NULL(false, 2003, "토큰이 존재하지 않습  니다."),

    private final int code;
    private final String message;

}
