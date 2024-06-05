package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.dto.res.NicknameExistResDto;

public interface AuthService {
    void addMember(MemberJoinReqDto memberJoinReqDto);

    MemberLoginResDto kakaoLogin(MemberLoginReqDto memberLoginReqDto);

    NicknameExistResDto checkNickname(String nickname);
}
