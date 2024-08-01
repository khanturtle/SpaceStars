package com.spacestar.back.auth.controller;

import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.service.AuthService;
import com.spacestar.back.auth.vo.req.MemberJoinReqVo;
import com.spacestar.back.auth.vo.req.MemberLoginReqVo;
import com.spacestar.back.auth.vo.res.*;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Auth", description = "auth")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ModelMapper mapper;
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


    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/nickname/{nickname}")
    public ResponseEntity<NicknameExistResVo> checkNickname(@PathVariable String nickname) {

        return new ResponseEntity<>(ResponseSuccess.NICKNAME_SUCCESS,
                mapper.map(authService.checkNickname(nickname), NicknameExistResVo.class));
    }

    @Operation(summary = "uuid로 닉네임 조회")
    @GetMapping("/nickname/search/{uuid}")
    public ResponseEntity<NicknameResVo> getNickname(@PathVariable String uuid){

        return new ResponseEntity<>(ResponseSuccess.NICKNAME_SELECT_SUCCESS,
                mapper.map(authService.getNickname(uuid), NicknameResVo.class));
    }

    @Operation(summary = "닉네임으로 uuid 찾기")
    @GetMapping("/uuid/search/{nickname}")
    public ResponseEntity<UuidResVo> getUuid(@PathVariable String nickname){

        return new ResponseEntity<>(ResponseSuccess.UUID_SELECT_SUCCESS,
                mapper.map(authService.getUuid(nickname), UuidResVo.class));
    }


    @Operation(summary = "닉네임 검색")
    @GetMapping("/search")
    public ResponseEntity<List<FriendSearchResVo>> searchNickname(@RequestParam("nickname") String nickname){
        return new ResponseEntity<>(ResponseSuccess.NICKNAME_SEARCH_SUCCESS,
                authService.searchNickname(nickname).stream()
                        .map(friendSearchResDto -> mapper.map(friendSearchResDto, FriendSearchResVo.class))
                        .toList());
    }
}
