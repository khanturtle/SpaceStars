package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),

    SWIPE_ADD_SUCCESS(HttpStatus.OK.value(), "채팅 요청 보내기 성공"),
    SWIPE_GET_SUCCESS(HttpStatus.OK.value(), "받은 채팅 요청 목록 조회 성공"),
    SWIPE_SENT_GET_SUCCESS(HttpStatus.OK.value(), "보낸 채팅 요청 목록 조회 성공"),
    SWIPE_AGREE_SUCCESS(HttpStatus.OK.value(), "채팅 요청 수락 성공"),
    SWIPE_REJECT_SUCCESS(HttpStatus.OK.value(), "채팅 요청 거절 성공");

    private final int code;
    private final String message;
}
