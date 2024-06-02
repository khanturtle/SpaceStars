package com.spacestar.back.profile.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.dto.res.ProfileImageListResDto;
import com.spacestar.back.profile.dto.res.ProfileMainImageResDto;
import com.spacestar.back.profile.repository.ProfileImageRepository;
import com.spacestar.back.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileImageRepository profileImageRepository;
    private final ModelMapper mapper;
    @Override
    public void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        List<ProfileImage> profileImages = profileImageRepository.findAllByProfile(profile);

        //사진 삭제
        for (ProfileImage profileImage : profileImages) {
            boolean check = false;
            for (ProfileImageReqDto profileImageReqDto : profileImageReqDtos) {
                if (profileImage.getProfileImageUrl().equals(profileImageReqDto.getProfileImageUrl())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                profileImageRepository.delete(profileImage);
            }
        }

        // 사진 저장
        for (ProfileImageReqDto profileImageReqDto : profileImageReqDtos) {
            boolean check = false;
            for (ProfileImage profileImage : profileImages) {
                //사진 존재
                if (profileImageReqDto.getProfileImageUrl().equals(profileImage.getProfileImageUrl())) {
                    profileImageRepository.save(profileImageReqDto.updateImage(profileImage, profileImageReqDto));
                    check = true;
                }
            }
            //사진 존재하지 않음
            if (!check){
                profileImageRepository.save(profileImageReqDto.addNewImage(profile, profileImageReqDto));
            }
        }
    }

    @Override
    public List<ProfileImageListResDto> findProfileImageList(String uuid) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        List<ProfileImageListResDto> profileImageListResDtos = new ArrayList<>();

        int i = 0;
        for (ProfileImage profileImage : profileImageRepository.findAllByProfile(profile)){
            profileImageListResDtos.add(ProfileImageListResDto.convertToDto(i,profileImage));
            i++;
        }
        return profileImageListResDtos;
    }

    @Override
    public ProfileMainImageResDto findMainProfileImage(String uuid) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        return mapper.map(
                profileImageRepository.findByProfileAndMain(profile,true),ProfileMainImageResDto.class);
    }
}
