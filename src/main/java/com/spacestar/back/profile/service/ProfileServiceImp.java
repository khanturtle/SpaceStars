package com.spacestar.back.profile.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.repository.ProfileRepository;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spacestar.back.global.ResponseStatus;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto) {
        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        profileRepository.save(profileInfoReqDto.updateToEntity(profile.getId(), profile.getUuid(), profileInfoReqDto));
    }
}
