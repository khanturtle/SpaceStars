package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.res.NicknameResDto;

public interface MemberService {

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);

    NicknameResDto getNickname(String uuid);
}
