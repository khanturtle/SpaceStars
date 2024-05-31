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

    TOKEN_NOT_VALID(2002, "토큰이 유효하지 않습니다."),

    GAME_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게임 ID 입니다."),
    GAME_GENRE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게임 장르 ID 입니다."),

    GAME_CLASS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "직업이 없는 게임 입니다."),
    GAME_POSITION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "포지션이 없는 게임 입니다."),
    GAME_SERVER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "서버가 없는 게임 입니다."),
    GAME_TIER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "티어가 없는 게임 입니다.");


//    TOKEN_NULL(false, 2003, "토큰이 존재하지 않습니다."),


    private final int code;
    private final String message;

}
