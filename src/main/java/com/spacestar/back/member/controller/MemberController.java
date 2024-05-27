package com.spacestar.back.member.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import com.spacestar.back.member.dto.res.MemberSwipeResDto;
import com.spacestar.back.member.service.MemberService;
import com.spacestar.back.member.vo.req.MemberInfoReqVo;
import com.spacestar.back.member.vo.req.ProfileImageReqVo;

import com.spacestar.back.member.vo.res.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원")
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper mapper;


    @Operation(summary = "회원 정보 추가입력/ 회원 정보 수정")
    @PutMapping("/info/add")
    public ResponseEntity<Void> memberInfoAdd(@RequestHeader("UUID") String uuid,
                                              @RequestBody MemberInfoReqVo memberInfoReqVo){

        memberService.updateMemberInfo(uuid,mapper.map(memberInfoReqVo, MemberInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.MEMBER_INFO_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로필 사진 수정 및 피드 사진 추가")
    @PutMapping("/profile/image/update")
    public ResponseEntity<Void> profileImageUpdate(@RequestHeader("UUID") String uuid,
                                                   @RequestBody List<ProfileImageReqVo> profileImageReqVo){

        List<ProfileImageReqDto> profileImageReqDtos = profileImageReqVo.stream()
                .map(vo -> mapper.map(vo, ProfileImageReqDto.class))
                .toList();
        memberService.updateProfileImages(uuid, profileImageReqDtos);

        return new ResponseEntity<>(ResponseSuccess.PROFILE_IMAGE_UPDATE_SUCCESS);
    }

    @Operation(summary = "프로필 사진 리스트 조회")
    @GetMapping("/profile/image")
    public ResponseEntity<List<ProfileImageListResVo>> profileImageList(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.PROFILE_IMAGE_SELECT_SUCCESS, memberService.findProfileImageList(uuid)
                .stream()
                .map(dto -> mapper.map(dto, ProfileImageListResVo.class))
                .toList());
    }

    @Operation(summary = "대표 프로필 사진 조회")
    @GetMapping("/profile/image/main")
    public ResponseEntity<ProfileMainImageResVo> mainProfileImage(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(memberService.findMainProfileImage(uuid), ProfileMainImageResVo.class)
        );

    }

    @Operation(summary = "채팅 프로필 조회(간단)")
    @GetMapping("/profile/chatting")
    public ResponseEntity<ProfileChattingResVo> chattingProfileInfo(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.CHATTING_PROFILE_SELECT_SUCCESS,
                mapper.map(memberService.findChattingProfile(uuid), ProfileChattingResVo.class)
        );
    }

    @Operation(summary = "매칭 프로필 조회(상세 포함)")
    @GetMapping("/profile/matching")
    public ResponseEntity<ProfileMatchingResVo> matchingProfileInfo(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(ResponseSuccess.MATCHING_PROFILE_SELECT_SUCCESS,
                mapper.map(memberService.findMatchingProfile(uuid), ProfileMatchingResVo.class));

    }

    @Operation(summary = "스와이프 추천 여부 조회")
    @GetMapping("/swipe/recommend")
    public ResponseEntity<MemberSwipeResVo> swipeRecommend(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_SELECT_SUCCESS,
                mapper.map(memberService.findSwipeRecommend(uuid), MemberSwipeResVo.class));
    }

    @Operation(summary = "스와이프 추천 여부 변경")
    @PatchMapping("/swipe/recommend/update")
    public ResponseEntity<Void> swipeRecommendUpdate(@RequestHeader("UUID") String uuid,
                                                     @RequestBody MemberSwipeResVo memberSwipeResVo) {

        memberService.updateSwipeRecommend(uuid, mapper.map(memberSwipeResVo, MemberSwipeResDto.class));
        return new ResponseEntity<>(ResponseSuccess.SWIPE_RECOMMEND_UPDATE_SUCCESS);
    }

    @Operation(summary = "회원 탈퇴(자발적)")
    @PatchMapping("/withdrawal")
    public ResponseEntity<Void> withdrawal(@RequestHeader("UUID") String uuid){

        memberService.withdrawal(uuid);
        return new ResponseEntity<>(ResponseSuccess.WITHDRAWAL_SUCCESS);
    }

    @Transactional
    @Operation(summary = "회원 탈퇴(영구 탈퇴)" )
    @PatchMapping("/withdrawal/force")
    public ResponseEntity<Void> withdrawalForce(@RequestHeader("UUID") String uuid){

        memberService.withdrawalForce(uuid);
        return new ResponseEntity<>(ResponseSuccess.WITHDRAWAL_FORCE_SUCCESS);
    }
}
