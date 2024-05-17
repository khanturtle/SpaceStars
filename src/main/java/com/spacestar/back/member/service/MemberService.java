package com.spacestar.back.member.service;

import com.spacestar.back.member.dto.req.MemberJoinReqDto;
import com.spacestar.back.member.dto.res.NicknameResDto;

public interface MemberService {

    void addMember(MemberJoinReqDto memberJoinReqDto);

    NicknameResDto duplicationNickname(String nickname);
}
