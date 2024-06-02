package com.spacestar.back.auth.controller;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.jwt.JWTUtil;
import com.spacestar.back.auth.service.AuthService;
import com.spacestar.back.auth.vo.req.MemberInfoReqVo;
import com.spacestar.back.auth.vo.req.MemberJoinReqVo;
import com.spacestar.back.auth.vo.req.MemberLoginReqVo;
import com.spacestar.back.auth.vo.res.MemberLoginResVo;
import com.spacestar.back.auth.vo.res.NicknameResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Auth", description = "auth")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ModelMapper mapper;
    private final JWTUtil jwtUtil;
    private final AuthService authService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberJoinReqVo memberJoinReqVo) {

        authService.addMember(mapper.map(memberJoinReqVo, MemberJoinReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.SIGNUP_SUCCESS);
    }

    @Operation(summary = "카카오 로그인")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResVo> login(@RequestBody MemberLoginReqVo memberLoginReqVo) {

        MemberLoginResDto memberLoginResDto = authService.kakaoLogin(mapper.map(memberLoginReqVo, MemberLoginReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.LOGIN_SUCCESS, mapper.map(memberLoginResDto, MemberLoginResVo.class));
    }

    @Operation(summary = "회원 정보 수정")
    @PostMapping("/info/update")
    public ResponseEntity<Void> updateMemberInfo(@RequestHeader("UUID") String uuid,
                                                 @RequestBody MemberInfoReqVo memberInfoReqVo){

        authService.updateMemberInfo(uuid, mapper.map(memberInfoReqVo, MemberInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.MEMBER_INFO_UPDATE_SUCCESS);
    }
}
