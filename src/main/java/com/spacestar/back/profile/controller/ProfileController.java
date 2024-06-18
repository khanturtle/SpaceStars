package com.spacestar.back.profile.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.profile.dto.req.*;
import com.spacestar.back.profile.dto.res.ProfileSwipeResDto;
import com.spacestar.back.profile.service.ProfileService;
import com.spacestar.back.profile.vo.req.*;
import com.spacestar.back.profile.vo.res.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final ModelMapper mapper;

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "기본 프로필 정보 추가 입력 및 수정")
    @PatchMapping("/info")
    public ResponseEntity<Void> updateProfileInfo(@RequestHeader("UUID") String uuid,
                                                  @RequestBody ProfileInfoReqVo profileInfoReqVo) {

        profileService.updateProfileInfo(uuid, mapper.map(profileInfoReqVo, ProfileInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_UPDATE_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "좋아하는 게임 정보 추가 입력 및 수정")
    @PatchMapping("/liked-game")
    public ResponseEntity<Void> updateLikedGameInfo(@RequestHeader("UUID") String uuid,
                                                    @RequestBody LikedGameInfoReqVo likedGameInfoReqVo) {

        profileService.updateLikedGameInfo(uuid, mapper.map(likedGameInfoReqVo, LikedGameInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_UPDATE_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "내가 하는 게임 정보 추가 입력 및 수정")
    @PatchMapping("/play-game")
    public ResponseEntity<Void> updatePlayGameInfo(@RequestHeader("UUID") String uuid,
                                                   @RequestBody List<PlayGameInfoReqVo> playGameInfoReqVos) {

        profileService.updatePlayGameInfo(uuid, playGameInfoReqVos.stream()
                .map(playGameInfoReqVo -> mapper.map(playGameInfoReqVo, PlayGameInfoReqDto.class))
                .toList());
        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_UPDATE_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "프로필 정보 조회")
    @GetMapping("/info")
    public ResponseEntity<ProfileInfoResVo> getProfileInfo(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_SELECT_SUCCESS,
                mapper.map(profileService.getProfileInfo(uuid), ProfileInfoResVo.class));
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "다른 회원 프로필 정보 조회")
    @GetMapping("/info/{uuid}")
    public ResponseEntity<ProfileInfoResVo> getYourProfileInfo(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_SELECT_SUCCESS,
                mapper.map(profileService.getProfileInfo(targetUuid), ProfileInfoResVo.class));
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "좋아하는 게임 조회")
    @GetMapping("/liked-game")
    public ResponseEntity<ProfileLikedGameResVo> getLikedGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_LIKED_GAME_SELECT_SUCCESS,
                mapper.map(profileService.getLikedGame(uuid), ProfileLikedGameResVo.class));
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "다른 회원 좋아하는 게임 조회")
    @GetMapping("/liked-game/{uuid}")
    public ResponseEntity<ProfileLikedGameResVo> getYourLikedGame(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_LIKED_GAME_SELECT_SUCCESS,
                mapper.map(profileService.getLikedGame(targetUuid), ProfileLikedGameResVo.class));
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "플레이한 게임 조회")
    @GetMapping("/play-game")
    public ResponseEntity<List<ProfilePlayGameInfoResVo>> getPlayGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_PLAY_GAME_SELECT_SUCCESS,
                profileService.getPlayGame(uuid).stream()
                        .map(playGame -> mapper.map(playGame, ProfilePlayGameInfoResVo.class))
                        .toList());
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "다른 회원 플레이한 게임 조회")
    @GetMapping("/play-game/{uuid}")
    public ResponseEntity<List<ProfilePlayGameInfoResVo>> getYourPlayGame(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_PLAY_GAME_SELECT_SUCCESS,
                profileService.getPlayGame(targetUuid).stream()
                        .map(playGame -> mapper.map(playGame, ProfilePlayGameInfoResVo.class))
                        .toList());
    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "프로필 사진 리스트 조회")
    @GetMapping("/image")
    public ResponseEntity<List<ProfileImageListResVo>> profileImageList(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, profileService.findProfileImageList(uuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "다른 회원 프로필 사진 리스트 조회")
    @GetMapping("/image/{uuid}")
    public ResponseEntity<List<ProfileImageListResVo>> profileYourImageList(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, profileService.findProfileImageList(targetUuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "대표 프로필 사진 조회")
    @GetMapping("/image/main")
    public ResponseEntity<ProfileMainImageResVo> mainProfileImage(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(profileService.findMainProfileImage(uuid), ProfileMainImageResVo.class)
        );

    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "다른 회원 대표 프로필 사진 조회")
    @GetMapping("/image/main/{uuid}")
    public ResponseEntity<ProfileMainImageResVo> mainProfileYourImage(@PathVariable("uuid") String targetUuid) {

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(profileService.findMainProfileImage(targetUuid), ProfileMainImageResVo.class)
        );

    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "프로필 사진 추가")
    @PostMapping("/image")
    public ResponseEntity<Void> addProfileImage(@RequestHeader("UUID") String uuid,
                                                @RequestBody ProfileImageReqVo profileImageReqVo) {

        profileService.addProfileImage(uuid, mapper.map(profileImageReqVo, ProfileImageReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_ADD_SUCCESS);
    }

    @Tag(name = "ProfileImage", description = "프로필 사진")
    @Operation(summary = "프로필 사진 삭제")
    @DeleteMapping("/image")
    public ResponseEntity<Void> deleteProfileImage(@RequestHeader("UUID") String uuid,
                                                   @RequestBody ProfileImageDeleteReqVo profileImageDeleteReqVo) {

        profileService.deleteProfileImage(uuid, mapper.map(profileImageDeleteReqVo, ProfileImageDeleteReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_DELETE_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "최초 로그인 시 프로필 생성")
    @Description("로그인 시 isProfile이 false인 경우(최초 로그인인 경우)에만 호출")
    @PostMapping("/exist")
    public ResponseEntity<Void> existProfile(@RequestHeader("UUID") String uuid) {

        profileService.existProfile(uuid);
        return new ResponseEntity<>(ResponseSuccess.PROFILE_EXIST_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "스와이프 추천 여부 조회")
    @GetMapping("/swipe/recommend")
    public ResponseEntity<ProfileSwipeResVo> swipeRecommend(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_SELECT_SUCCESS,
                mapper.map(profileService.findSwipeRecommend(uuid), ProfileSwipeResVo.class));
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "스와이프 추천 여부 변경")
    @PatchMapping("/swipe/recommend")
    public ResponseEntity<Void> swipeRecommendUpdate(@RequestHeader("UUID") String uuid,
                                                     @RequestBody ProfileSwipeResVo profileSwipeResVo) {

        profileService.updateSwipeRecommend(uuid, mapper.map(profileSwipeResVo, ProfileSwipeResDto.class));
        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_UPDATE_SUCCESS);
    }

    @Tag(name = "Profile", description = "프로필")
    @Operation(summary = "대표 게임 id 조회")
    @GetMapping("/main-game")
    public ResponseEntity<MainGameResVo> getMainGameId(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.MAIN_GAME_ID_SELECT_SUCCESS,
                mapper.map(profileService.getMainGameId(uuid), MainGameResVo.class));
    }

    @Tag(name = "Select", description = "조회용")
    @Operation(summary = "빠른 매칭용 사용자 정보 조회")
    @GetMapping("/quick-matching/{uuid}")
    public ResponseEntity<QuickMemberInfoResVo> quickMemberInfo(@PathVariable("uuid") String uuid){

        return new ResponseEntity<>(ResponseSuccess.QUICK_MEMBER_INFO_SELECT_SUCCESS,
                mapper.map(profileService.quickMemberInfo(uuid), QuickMemberInfoResVo.class));
    }


}