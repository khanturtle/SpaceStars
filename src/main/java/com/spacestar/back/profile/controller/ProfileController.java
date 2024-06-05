package com.spacestar.back.profile.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.service.ProfileService;
import com.spacestar.back.profile.vo.req.ProfileImageReqVo;
import com.spacestar.back.profile.vo.res.ProfileImageListResVo;
import com.spacestar.back.profile.vo.res.ProfileMainImageResVo;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.vo.res.ProfileInfoResVo;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import com.spacestar.back.profile.vo.res.ProfileLikedGameResVo;
import com.spacestar.back.profile.vo.res.ProfilePlayGameInfoResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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


    @Operation(summary = "프로필 정보 추가 입력 및 수정")
    @PutMapping("/info/update")
    public ResponseEntity<Void> updateProfileInfo(@RequestHeader("UUID") String uuid,
                                                  @RequestBody ProfileInfoReqVo profileInfoReqVo) {

        profileService.updateProfileInfo(uuid, mapper.map(profileInfoReqVo, ProfileInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로필 정보 조회")
    @GetMapping("/info")
    public ResponseEntity<ProfileInfoResVo> getProfileInfo(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_INFO_SELECT_SUCCESS,
                mapper.map(profileService.getProfileInfo(uuid), ProfileInfoResVo.class));
    }

    @Operation(summary = "좋아하는 게임 조회")
    @GetMapping("/liked-game")
    public ResponseEntity<ProfileLikedGameResVo> getLikedGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_LIKED_GAME_SELECT_SUCCESS,
                mapper.map(profileService.getLikedGame(uuid), ProfileLikedGameResVo.class));
    }

    @Operation(summary = "플레이한 게임 조회")
    @GetMapping("/play-game")
    public ResponseEntity<List<ProfilePlayGameInfoResVo>> getPlayGame(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.PROFILE_PLAY_GAME_SELECT_SUCCESS,
                profileService.getPlayGame(uuid).stream()
                        .map(playGame -> mapper.map(playGame, ProfilePlayGameInfoResVo.class))
                        .toList());
    }
    @Operation(summary = "프로필 사진 수정 및 피드 사진 추가")
    @PutMapping("/profile/image/update")
    public ResponseEntity<Void> profileImageUpdate(@RequestHeader("UUID") String uuid,
                                                   @RequestBody List<ProfileImageReqVo> profileImageReqVo){

        List<ProfileImageReqDto> profileImageReqDtos = profileImageReqVo.stream()
                .map(vo -> mapper.map(vo, ProfileImageReqDto.class))
                .toList();
        profileService.updateProfileImages(uuid, profileImageReqDtos);

        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로필 사진 리스트 조회")
    @GetMapping("/profile/image")
    public ResponseEntity<List<ProfileImageListResVo>> profileImageList(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, profileService.findProfileImageList(uuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Operation(summary = "대표 프로필 사진 조회")
    @GetMapping("/profile/image/main")
    public ResponseEntity<ProfileMainImageResVo> mainProfileImage(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(profileService.findMainProfileImage(uuid), ProfileMainImageResVo.class)
        );

    }

}