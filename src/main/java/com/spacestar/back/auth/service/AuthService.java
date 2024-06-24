package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.*;

import java.util.List;

public interface AuthService {
    void addMember(MemberJoinReqDto memberJoinReqDto);

    MemberLoginResDto kakaoLogin(MemberLoginReqDto memberLoginReqDto);

    NicknameExistResDto checkNickname(String nickname);

    NicknameResDto getNickname(String uuid);

    UuidResDto getUuid(String nickname);

    List<FriendSearchResDto> searchNickname(String nickname);
}
