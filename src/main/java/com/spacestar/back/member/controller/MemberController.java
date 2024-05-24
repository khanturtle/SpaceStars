package com.spacestar.back.member.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import com.spacestar.back.member.dto.req.MemberJoinReqDto;
import com.spacestar.back.member.dto.req.MemberLoginReqDto;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import com.spacestar.back.member.jwt.JWTUtil;
import com.spacestar.back.member.service.MemberService;
import com.spacestar.back.member.vo.req.MemberInfoReqVo;
import com.spacestar.back.member.vo.req.MemberJoinReqVo;
import com.spacestar.back.member.vo.req.MemberLoginReqVo;
import com.spacestar.back.member.vo.req.ProfileImageReqVo;
import com.spacestar.back.member.vo.res.NicknameResVo;
import com.spacestar.back.member.vo.res.ProfileChattingResVo;
import com.spacestar.back.member.vo.res.ProfileImageListResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ProfileImageListResVo> mainProfileImage(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.MAIN_PROFILE_IMAGE_SELECT_SUCCESS,
                mapper.map(memberService.findMainProfileImage(uuid), ProfileImageListResVo.class)
        );

    }

    @Operation(summary = "채팅 프로필 조회(간단)")
    @GetMapping("/profile/chatting")
    public ResponseEntity<ProfileChattingResVo> chattingProfileImage(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(
                ResponseSuccess.CHATTING_PROFILE_SELECT_SUCCESS,
                mapper.map(memberService.findChattingProfile(uuid), ProfileChattingResVo.class)
        );
    }

}
