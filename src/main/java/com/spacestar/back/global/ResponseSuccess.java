package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),

    MESSAGE_CREATE_SUCCESS(201, "메시지 생성 성공"),

    CREATE_CHATROOM_SUCCESS(301, "채팅방 생성 성공"),
    GET_CHATROOM_LIST_SUCCESS(302, "채팅방 목록 조회 성공"),
    GET_CHATROOM_DETAIL_SUCCESS(303, "채팅방 상세 조회 성공"),


    CREATE_TEAM_CHATROOM_SUCCESS(401, "팀 채팅방 생성 성공"),


    INTERNAL_SERVER_ERROR(900, "Internal server error");


    private final int code;
    private final String message;

}
