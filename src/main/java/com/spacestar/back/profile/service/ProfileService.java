package com.spacestar.back.profile.service;

import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.res.ProfileInfoResDto;
import com.spacestar.back.profile.dto.res.ProfileLikedGameResDto;
import com.spacestar.back.profile.dto.res.ProfilePlayGameInfoResDto;

import java.util.List;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);

    ProfileInfoResDto getProfileInfo(String uuid);

    ProfileLikedGameResDto getLikedGame(String uuid);

    List<ProfilePlayGameInfoResDto> getPlayGame(String uuid);
}
