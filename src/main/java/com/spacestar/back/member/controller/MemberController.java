package com.spacestar.back.member.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.member.dto.req.MemberJoinReqDto;
import com.spacestar.back.member.dto.res.NicknameResDto;
import com.spacestar.back.member.service.MemberService;
import com.spacestar.back.member.vo.req.MemberJoinReqVo;
import com.spacestar.back.member.vo.res.NicknameResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원")
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper mapper;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Void> memberInfoAdd(@RequestBody @Valid MemberJoinReqVo memberJoinReqVo) {

        memberService.addMember(mapper.map(memberJoinReqVo, MemberJoinReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.SIGNUP_SUCCESS);
    }

    @Operation(summary = "닉네임 중복 검증")
    @GetMapping("/duplication/{nickname}")
    public ResponseEntity<NicknameResVo> duplicationNickname(@PathVariable("nickname") String nickname) {
        return new ResponseEntity<>(ResponseSuccess.DUPLICATION_NICKNAME_SUCCESS,mapper.map(memberService.duplicationNickname(nickname), NicknameResVo.class));
    }
}
