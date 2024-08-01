package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    //게임 장르
    GET_GAME_GENRE_SUCCESS(HttpStatus.OK.value(), "게임 장르 조회 성공"),
    GAME_GENRE_ADD_SUCCESS(HttpStatus.CREATED.value(), "게임 장르 생성 성공"),
    GAME_GENRE_DELETE_SUCCESS(HttpStatus.OK.value(), "게임 장르 삭제 성공"),

    //게임
    GET_GAMES_SUCCESS(HttpStatus.OK.value(), "전체 게임 조회 성공"),
    GET_GAME_OPTION_SUCCESS(HttpStatus.OK.value(), "게임 옵션 조회 성공"),
    GAME_ADD_SUCCESS(HttpStatus.CREATED.value(), "게임 생성 성공"),
    GAME_DELETE_SUCCESS(HttpStatus.OK.value(), "게임 삭제 성공"),

    //게임 상세
    GET_GAME_CLASS_SUCCESS(HttpStatus.OK.value(), "게임 직업 조회 성공"),
    GET_GAME_POSITION_SUCCESS(HttpStatus.OK.value(), "게임 포지션 조회 성공"),
    GET_GAME_SERVER_SUCCESS(HttpStatus.OK.value(), "게임 서버 조회 성공"),
    GET_GAME_TIER_SUCCESS(HttpStatus.OK.value(), "게임 티어 조회 성공"),

    ADD_GAME_CLASS_SUCCESS(HttpStatus.CREATED.value(), "게임 직업 생성 성공"),
    DELETE_GAME_CLASS_SUCCESS(HttpStatus.OK.value(), "게임 직업 삭제 성공"),
    ADD_GAME_POSITION_SUCCESS(HttpStatus.CREATED.value(), "게임 포지션 생성 성공"),
    DELETE_GAME_POSITION_SUCCESS(HttpStatus.OK.value(), "게임 포지션 삭제 성공"),
    ADD_GAME_SERVER_SUCCESS(HttpStatus.OK.value(), "게임 서버 생성 성공"),
    DELETE_GAME_SERVER_SUCCESS(HttpStatus.OK.value(), "게임 서버 삭제 성공"),
    ADD_GAME_TIER_SUCCESS(HttpStatus.OK.value(), "게임 티어 생성 성공"),
    DELETE_GAME_TIER_SUCCESS(HttpStatus.OK.value(), "게임 티어 삭제 성공"),
    GET_GAME_SUCCESS(HttpStatus.OK.value(), "게임 단건 조회 성공");


    private final int code;
    private final String message;
}
