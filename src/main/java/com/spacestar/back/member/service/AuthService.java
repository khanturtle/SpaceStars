package com.spacestar.back.member.service;

import com.spacestar.back.member.dto.req.MemberJoinReqDto;
import com.spacestar.back.member.dto.req.MemberLoginReqDto;
import com.spacestar.back.member.dto.res.MemberLoginResDto;
import com.spacestar.back.member.dto.res.NicknameResDto;

public interface AuthService {
    void addMember(MemberJoinReqDto memberJoinReqDto);

    NicknameResDto duplicationNickname(String nickname);

    MemberLoginResDto kakaoLogin(MemberLoginReqDto memberLoginReqDto);

}
