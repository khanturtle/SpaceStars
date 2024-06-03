package com.spacestar.back.profile.service;

import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.res.ProfileInfoResDto;
import com.spacestar.back.profile.dto.res.ProfileLikedGameResDto;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import com.spacestar.back.profile.vo.res.ProfileInfoResVo;
import com.spacestar.back.profile.vo.res.ProfileLikedGameResVo;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);

    ProfileInfoResDto getProfileInfo(String uuid);

    ProfileLikedGameResDto getLikedGame(String uuid);
}
