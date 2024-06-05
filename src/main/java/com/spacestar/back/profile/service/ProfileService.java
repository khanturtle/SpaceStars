package com.spacestar.back.profile.service;


import com.spacestar.back.profile.dto.req.KakaoProfileImageReqDto;
import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.res.*;

import java.util.List;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);

    ProfileInfoResDto getProfileInfo(String uuid);

    ProfileLikedGameResDto getLikedGame(String uuid);

    List<ProfilePlayGameInfoResDto> getPlayGame(String uuid);

    void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos);

    List<ProfileImageListResDto> findProfileImageList(String uuid);

    ProfileMainImageResDto findMainProfileImage(String uuid);

    void addProfileImage(String uuid, KakaoProfileImageReqDto kakaoProfileImageReqDto);

    ProfileExistResDto existProfile(String uuid);

    ProfileSwipeResDto findSwipeRecommend(String uuid);

    void updateSwipeRecommend(String uuid, ProfileSwipeResDto profileSwipeResDto);
}
