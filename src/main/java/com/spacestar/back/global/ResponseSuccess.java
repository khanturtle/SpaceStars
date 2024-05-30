package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    //게임
    GET_GAMES_SUCCESS(HttpStatus.OK.value(),"전체 게임 조회 성공"),
    GET_GAME_OPTION_SUCCESS(HttpStatus.OK.value(),"게임 옵션 조회 성공"),

    //게임 옵션
    GET_GAME_CLASS_SUCCESS(HttpStatus.OK.value(), "게임 직업 조회 성공"),
    GET_GAME_POSITION_SUCCESS(HttpStatus.OK.value(), "게임 포지션 조회 성공"),
    GET_GAME_SERVER_SUCCESS(HttpStatus.OK.value(), "게임 서버 조회 성공"),
    GET_GAME_TIER_SUCCESS(HttpStatus.OK.value(), "게임 티어 조회 성공"),

    //게임 장르
    GET_GAME_GENRE_SUCCESS(HttpStatus.OK.value(),"게임 장르 조회 성공"),
    GAME_GENRE_ADD_SUCCESS(HttpStatus.CONTINUE.value(), "게임 장르 생성 성공"),
    GAME_GENRE_DELETE_SUCCESS(HttpStatus.OK.value(), "게임 장르 삭제 성공");


    private final int code;
    private final String message;
}
