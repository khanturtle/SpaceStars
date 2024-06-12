package com.spacestar.back.profile.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.profile.dto.req.KakaoProfileImageReqDto;
import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.dto.res.ProfileSwipeResDto;
import com.spacestar.back.profile.service.ProfileService;
import com.spacestar.back.profile.vo.req.KakaoProfileImageReqVo;
import com.spacestar.back.profile.vo.req.ProfileImageReqVo;
import com.spacestar.back.profile.vo.res.*;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Profile", description = "프로필")
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final ModelMapper mapper;


    @Operation(summary = "기본 프로필 정보 추가 입력 및 수정")
    @PutMapping("/info/update")
    public ResponseEntity<Void> updateProfileInfo(@RequestHeader("UUID") String uuid,
                                                  @RequestBody ProfileInfoReqVo profileInfoReqVo) {

        profileService.updateProfileInfo(uuid, mapper.map(profileInfoReqVo, ProfileInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로필 정보 조회")
    @GetMapping("/info")
    public ResponseEntity<ProfileInfoResVo> getProfileInfo(@RequestHeader("UUID") String uuid ){

        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_SELECT_SUCCESS,
                mapper.map(profileService.getProfileInfo(uuid), ProfileInfoResVo.class));
    }
    @Operation(summary = "다른 회원 프로필 정보 조회")
    @GetMapping("/info/{uuid}")
    public ResponseEntity<ProfileInfoResVo> getYourProfileInfo(@PathVariable("uuid") String targetUuid){

        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_SELECT_SUCCESS,
                mapper.map(profileService.getProfileInfo(targetUuid), ProfileInfoResVo.class));
    }


    @Operation(summary = "좋아하는 게임 조회")
    @GetMapping("/liked-game")
    public ResponseEntity<ProfileLikedGameResVo> getLikedGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_LIKED_GAME_SELECT_SUCCESS,
                mapper.map(profileService.getLikedGame(uuid), ProfileLikedGameResVo.class));
    }

    @Operation(summary = "다른 회원 좋아하는 게임 조회")
    @GetMapping("/liked-game/{uuid}")
    public ResponseEntity<ProfileLikedGameResVo> getYourLikedGame(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_LIKED_GAME_SELECT_SUCCESS,
                mapper.map(profileService.getLikedGame(targetUuid), ProfileLikedGameResVo.class));
    }

    @Operation(summary = "플레이한 게임 조회")
    @GetMapping("/play-game")
    public ResponseEntity<List<ProfilePlayGameInfoResVo>> getPlayGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_PLAY_GAME_SELECT_SUCCESS,
                profileService.getPlayGame(uuid).stream()
                        .map(playGame -> mapper.map(playGame, ProfilePlayGameInfoResVo.class))
                        .toList());
    }

    @Operation(summary = "다른 회원 플레이한 게임 조회")
    @GetMapping("/play-game/{uuid}")
    public ResponseEntity<List<ProfilePlayGameInfoResVo>> getYourPlayGame(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_PLAY_GAME_SELECT_SUCCESS,
                profileService.getPlayGame(targetUuid).stream()
                        .map(playGame -> mapper.map(playGame, ProfilePlayGameInfoResVo.class))
                        .toList());
    }

    @Operation(summary = "프로필 사진 리스트 조회")
    @GetMapping("/image")
    public ResponseEntity<List<ProfileImageListResVo>> profileImageList(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, profileService.findProfileImageList(uuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Operation(summary = "다른 회원 프로필 사진 리스트 조회")
    @GetMapping("/image/{uuid}")
    public ResponseEntity<List<ProfileImageListResVo>> profileYourImageList(@PathVariable("uuid") String targetUuid){

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, profileService.findProfileImageList(targetUuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Operation(summary = "대표 프로필 사진 조회")
    @GetMapping("/image/main")
    public ResponseEntity<ProfileMainImageResVo> mainProfileImage(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(profileService.findMainProfileImage(uuid), ProfileMainImageResVo.class)
        );

    }

    @Operation(summary = "다른 회원 대표 프로필 사진 조회")
    @GetMapping("/image/main/{uuid}")
    public ResponseEntity<ProfileMainImageResVo> mainProfileYourImage(@PathVariable("uuid") String targetUuid){

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(profileService.findMainProfileImage(targetUuid), ProfileMainImageResVo.class)
        );

    }

    @Operation(summary = "프로필 사진 추가")
    @PostMapping("/image/add")
    public ResponseEntity<Void> addProfileImage(@RequestHeader("UUID") String uuid,
                                                @RequestBody ProfileImageReqVo profileImageReqVo) {

        profileService.addProfileImage(uuid, mapper.map(profileImageReqVo, ProfileImageReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_ADD_SUCCESS);
    }

    @Operation(summary = "프로필 사진 삭제")
    @PostMapping("/image/delete")
    public ResponseEntity<Void> deleteProfileImage(@RequestHeader("UUID") String uuid,
                                                @RequestBody ProfileImageReqVo profileImageReqVo) {

        profileService.deleteProfileImage(uuid, mapper.map(profileImageReqVo, ProfileImageReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_ADD_SUCCESS);
    }

    @Operation(summary = "메인 프로필 사진으로 설정")
    @PostMapping("/image/main/update")
    public ResponseEntity<Void> mainProfileImage(@RequestHeader("UUID") String uuid,
                                                @RequestBody ProfileImageReqVo profileImageReqVo) {

        profileService.mainProfileImage(uuid, mapper.map(profileImageReqVo, ProfileImageReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_ADD_SUCCESS);
    }

    @Operation(summary = "로그인 시 프로필 존재 유무 확인")
    @Description("프로필 존재 유무 확인 후 없으면 기본 프로필 생성"+ "게임 빈 칸이면 false, 모두 채워져 있으면 true")
    @GetMapping("/exist")
    public ResponseEntity<ProfileExistResVo> existProfile(@RequestHeader("UUID") String uuid){

            return new ResponseEntity<>(ResponseSuccess.PROFILE_EXIST_SUCCESS,
                    mapper.map(profileService.existProfile(uuid), ProfileExistResVo.class));
    }

    @Operation(summary = "스와이프 추천 여부 조회")
    @GetMapping("/swipe/recommend")
    public ResponseEntity<ProfileSwipeResVo> swipeRecommend(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_SELECT_SUCCESS,
                mapper.map(profileService.findSwipeRecommend(uuid), ProfileSwipeResVo.class));
    }

    @Operation(summary = "스와이프 추천 여부 변경")
    @PatchMapping("/swipe/recommend/update")
    public ResponseEntity<Void> swipeRecommendUpdate(@RequestHeader("UUID") String uuid,
                                                     @RequestBody ProfileSwipeResVo profileSwipeResVo) {

        profileService.updateSwipeRecommend(uuid, mapper.map(profileSwipeResVo, ProfileSwipeResDto.class));
        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_UPDATE_SUCCESS);
    }

}