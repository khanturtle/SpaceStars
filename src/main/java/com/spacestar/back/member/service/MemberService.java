package com.spacestar.back.member.service;

import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import com.spacestar.back.member.dto.res.MemberSwipeResDto;
import com.spacestar.back.member.dto.res.ProfileChattingResDto;
import com.spacestar.back.member.dto.res.ProfileImageListResDto;
import com.spacestar.back.member.dto.res.ProfileMatchingResDto;

import java.util.List;

public interface MemberService {

    void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto);

    void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos);

    List<ProfileImageListResDto> findProfileImageList(String uuid);

    ProfileImageListResDto findMainProfileImage(String uuid);

    ProfileChattingResDto findChattingProfile(String uuid);

    ProfileMatchingResDto findMatchingProfile(String uuid);

    MemberSwipeResDto findSwipeRecommend(String uuid);

    void updateSwipeRecommend(String uuid, MemberSwipeResDto memberSwipeResDto);
}
