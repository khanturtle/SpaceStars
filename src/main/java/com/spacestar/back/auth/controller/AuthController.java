package com.spacestar.back.auth.controller;

import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.jwt.JWTUtil;
import com.spacestar.back.auth.service.AuthService;
import com.spacestar.back.auth.vo.req.MemberJoinReqVo;
import com.spacestar.back.auth.vo.req.MemberLoginReqVo;
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

//    @Operation(summary = "닉네임 중복 검증")
//    @GetMapping("/duplication/{nickname}")
//    public ResponseEntity<NicknameResVo> duplicationNickname(@PathVariable("nickname") String nickname) {
//        return new ResponseEntity<>(ResponseSuccess.DUPLICATION_NICKNAME_SUCCESS,mapper.map(authService.duplicationNickname(nickname), NicknameResVo.class));
//    }

    @Operation(summary = "카카오 로그인")
    @PostMapping("/login")
    public ResponseEntity<HttpHeaders> login(@RequestBody MemberLoginReqVo memberLoginReqVo){

        MemberLoginResDto memberLoginResDto = authService.kakaoLogin(mapper.map(memberLoginReqVo, MemberLoginReqDto.class));
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, memberLoginResDto.getAccessToken());
        headers.set("uuid", memberLoginResDto.getUuid());

        return new ResponseEntity<>(ResponseSuccess.LOGIN_SUCCESS, headers);
    }

}
