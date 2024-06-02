package com.spacestar.back.profile.service;

import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;

public interface ProfileService {
    void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto);
}
