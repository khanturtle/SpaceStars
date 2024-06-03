package com.spacestar.back.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


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
    PROFILE_PLAY_GAME_SELECT_SUCCESS(200, "내가 하는 게임 조회 성공" );



    private final int code;
    private final String message;

}
