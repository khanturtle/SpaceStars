package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;

public interface MemberService {

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);
}
