package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    GET_GAMES_SUCCESS(HttpStatus.OK.value(),"전체 게임 조회 성공");



    private final int code;
    private final String message;
}
