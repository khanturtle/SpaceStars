package com.spacestar.back.member.service;

import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import com.spacestar.back.member.dto.res.*;

import java.util.List;

public interface MemberService {

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);

    void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos);

    List<ProfileImageListResDto> findProfileImageList(String uuid);

    ProfileMainImageResDto findMainProfileImage(String uuid);

    ProfileChattingResDto findChattingProfile(String uuid);

    ProfileMatchingResDto findMatchingProfile(String uuid);

    MemberSwipeResDto findSwipeRecommend(String uuid);

    void updateSwipeRecommend(String uuid, MemberSwipeResDto memberSwipeResDto);

    void withdrawal(String uuid);

    void withdrawalForce(String uuid);
}
