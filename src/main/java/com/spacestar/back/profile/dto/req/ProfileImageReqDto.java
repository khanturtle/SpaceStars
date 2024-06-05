package com.spacestar.back.profile.dto.req;

import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImageReqDto {

    private String profileImageUrl;
    private boolean mainImage;
    private int idx;

    public ProfileImage updateImage(ProfileImage profileImage, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .id(profileImage.getId())
                .profile(profileImage.getProfile())
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(profileImageReqDto.isMainImage())
                .idx(profileImageReqDto.getIdx())
                .build();

    }

    public ProfileImage addNewImage(Profile profile, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .profile(profile)
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(profileImageReqDto.isMainImage())
                .idx(profileImageReqDto.getIdx())
                .build();
    }
}
