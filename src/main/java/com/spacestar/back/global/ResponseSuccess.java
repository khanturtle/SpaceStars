package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),
    //스와이프
    SWIPE_ADD_SUCCESS(HttpStatus.CREATED.value(), "채팅 요청 보내기 성공"),
    SWIPE_GET_SUCCESS(HttpStatus.OK.value(), "받은 채팅 요청 목록 조회 성공"),
    SWIPE_SENT_GET_SUCCESS(HttpStatus.OK.value(), "보낸 채팅 요청 목록 조회 성공"),
    SWIPE_AGREE_SUCCESS(HttpStatus.OK.value(), "채팅 요청 수락 성공"),
    SWIPE_REJECT_SUCCESS(HttpStatus.OK.value(), "채팅 요청 거절 성공"),
    SWIPE_COUNT_SUCCESS(HttpStatus.OK.value(), "보낸 요청 횟수 조회 성공"),
    //빠른 매칭
    QUICK_MATCHING_ENTER_SUCCESS(HttpStatus.CREATED.value(),"빠른 매칭 큐 입장 성공"),
    QUICK_MATCHING_QUIT_SUCCESS(HttpStatus.OK.value(),"빠른 매칭 큐 취소"),
    QUICK_MATCHING_ACCEPT_SUCCESS(HttpStatus.OK.value(),"빠른 매칭 큐 수락 성공"),
    QUICK_MATCHING_REJECT_SUCCESS(HttpStatus.OK.value(),"빠른 매칭 큐 거절 성공"),
    QUICK_MATCHING_COMPLETE_SUCCESS(HttpStatus.OK.value(),"빠른 매칭 큐 성사 성공"),
    QUICK_MATCHING_COMPLETE_FAIL(HttpStatus.OK.value(),"빠른 매칭 큐 매칭 실패")
    ;


    private final int code;
    private final String message;
}
