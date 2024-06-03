package com.spacestar.back.profile.service;

import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.res.ProfileInfoResDto;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import com.spacestar.back.profile.vo.res.ProfileInfoResVo;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);

    ProfileInfoResDto getProfileInfo(String uuid);
}
