package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    //alarm
    ALARM_INSERT_SUCCESS(200, "알림 생성에 성공하였습니다."),
    ALARM_LIST_SELECT_SUCCESS(201, "알림 목록 조회를 성공하였습니다."),
    ALARM_STATE_SELECT_SUCCESS(202, "알림 상태 조회를 성공하였습니다."),
    ALARM_CHECK_STATE_UPDATE_SUCCESS(203, "알림 조회 상태 수정를 성공하였습니다."),
    ALARM_LIST_DELETE_SUCCESS(204, "알림 삭제를 성공하였습니다."),



    INTERNAL_SERVER_ERROR(900, "Internal server error"),

    TOKEN_NOT_VALID( 2002, "토큰이 유효하지 않습니다.");

    private final int code;
    private final String message;

}
