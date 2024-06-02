package com.spacestar.back.profile.service;

import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.dto.res.ProfileImageListResDto;
import com.spacestar.back.profile.dto.res.ProfileMainImageResDto;

import java.util.Collection;
import java.util.List;

public interface ProfileService {
    void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos);

    List<ProfileImageListResDto> findProfileImageList(String uuid);

    ProfileMainImageResDto findMainProfileImage(String uuid);
}
