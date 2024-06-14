package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.res.MemberInfoResDto;
import com.spacestar.back.auth.dto.res.NicknameResDto;
import com.spacestar.back.auth.dto.res.UuidResDto;

public interface MemberService {

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);

    NicknameResDto getNickname(String uuid);

    UuidResDto getUuid(String nickname);

    MemberInfoResDto findMemberInfo(String uuid);
}
