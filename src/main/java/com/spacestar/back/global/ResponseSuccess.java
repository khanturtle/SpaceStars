package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    SUCCESS(200, "요청에 성공했습니다."),
    SIGNUP_SUCCESS(200,"회원가입 성공" ),
    DUPLICATION_NICKNAME_SUCCESS(200, "닉네임 중복 검증 성공"),
    LOGIN_SUCCESS(200, "로그인 성공"),
    WITHDRAWAL_SUCCESS(200, "자발적 회원탈퇴 성공"),
    WITHDRAWAL_FORCE_SUCCESS(200, "회원 영구 탈퇴 성공"),
    MEMBER_INFO_UPDATE_SUCCESS(200, "회원 정보 수정 성공"),
    NICKNAME_SUCCESS(200,"닉네임 중복 확인 성공" ),
    NICKNAME_SELECT_SUCCESS(200,"닉네임 조회 성공" ),
    UUID_SELECT_SUCCESS(200, "uuid 조회 성공");



    private final int code;
    private final String message;

}
