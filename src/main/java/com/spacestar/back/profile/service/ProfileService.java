package com.spacestar.back.profile.service;


import com.spacestar.back.profile.dto.req.*;
import com.spacestar.back.profile.dto.res.*;

import java.util.List;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);

    ProfileInfoResDto getProfileInfo(String uuid);

    ProfileLikedGameResDto getLikedGame(String uuid);

    List<ProfilePlayGameInfoResDto> getPlayGame(String uuid);

    List<ProfileImageListResDto> findProfileImageList(String uuid);

    ProfileMainImageResDto findMainProfileImage(String uuid);

    void addProfileImage(String uuid, ProfileImageReqDto profileImageReqDto);

    void existProfile(String uuid);

    ProfileSwipeResDto findSwipeRecommend(String uuid);

    void updateSwipeRecommend(String uuid, ProfileSwipeResDto profileSwipeResDto);

    void deleteProfileImage(String uuid, ProfileImageDeleteReqDto profileImageDeleteReqDto);

    void updateLikedGameInfo(String uuid, LikedGameInfoReqDto likedGameInfoReqDto);

    void updatePlayGameInfo(String uuid, List<PlayGameInfoReqDto> playGameInfoReqDtos);

    QuickMemberInfoResDto quickMemberInfo(String uuid);

    MainGameResDto getMainGameId(String uuid);
}
