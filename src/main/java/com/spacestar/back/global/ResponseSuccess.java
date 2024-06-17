package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ResponseSuccess {

    //member-profile
    PROFILE_INFO_UPDATE_SUCCESS(200, "프로필 정보 수정 성공"),
    PROFILE_IMAGE_UPDATE_SUCCESS(200,"프로필 사진 수정 성공" ),
    PROFILE_IMAGE_SELECT_SUCCESS(200,"프로필 사진 조회 성공" ),
    MAIN_PROFILE_IMAGE_SELECT_SUCCESS(200,"메인 프로필 사진 조회 성공" ),
    SWIPE_RECOMMEND_SELECT_SUCCESS(200, "스와이프 추천 여부 조회 성공"),
    SWIPE_RECOMMEND_UPDATE_SUCCESS(200, "스와이프 추천 여부 수정 성공"),
    PROFILE_INFO_SELECT_SUCCESS(200, "프로필 정보 조회 성공"),
    PROFILE_LIKED_GAME_SELECT_SUCCESS(200, "좋아하는 게임 조회 성공"),
    PROFILE_PLAY_GAME_SELECT_SUCCESS(200, "내가 하는 게임 조회 성공" ),
    PROFILE_IMAGE_ADD_SUCCESS(200,"프로필 사진 추가 성공" ),
    PROFILE_EXIST_SUCCESS(200,"프로필 존재 여부 조회 성공" ),
    PROFILE_IMAGE_DELETE_SUCCESS(200,"프로필 사진 삭제 성공" ),

    //friend
    FRIEND_ADD_SUCCESS(200, "친구 신청 성공"),
    FRIEND_LIST_SELECT_SUCCESS(200,"친구 목록 조회 성공" ),
    FRIEND_REQUEST_SELECT_SUCCESS(200,"친구 요청 목록 조회 성공" ),
    FRIEND_ACCEPT_SUCCESS(200,"친구 요청 수락" ),
    FRIEND_REJECT_SUCCESS(200, "친구 요청 거절"),
    FRIEND_DELETE_SUCCESS(200,"친구 삭제 성공" ),
    FRIEND_IS_FRIEND_SUCCESS(200,"친구 여부 조회 성공" ),

    //report
    REPORT_ADD_SUCCESS(HttpStatus.OK.value(),"신고 성공"),

    //block
    BLOCK_ADD_SUCCESS(200, "차단 추가 성공"),
    BLOCK_DELETE_SUCCESS(200, "차단 취소 성공"),
    BLOCK_EXIST_SUCCESS(200, "차단 여부 조회 성공"),
    BLOCK_LIST_SELECT_SUCCESS(200, "차단 목록 조회 성공"),

    //select
    QUICK_MEMBER_INFO_SELECT_SUCCESS(200, "빠른 회원 정보 조회 성공");



    private final int code;
    private final String message;

}
