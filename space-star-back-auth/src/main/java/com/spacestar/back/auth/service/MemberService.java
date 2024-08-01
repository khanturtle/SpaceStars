package com.spacestar.back.auth.service;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.res.*;
import com.spacestar.back.auth.vo.res.FriendSearchResVo;

import java.util.List;

public interface MemberService {

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);

    MemberInfoResDto findMemberInfo(String uuid);

    QuickAuthInfoResDto findQuickAuthInfo(String uuid);

}
