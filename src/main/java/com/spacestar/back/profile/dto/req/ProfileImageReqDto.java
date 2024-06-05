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

    public ProfileImage updateImage(String uuid, ProfileImage profileImage, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .id(profileImage.getId())
                .uuid(uuid)
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(profileImageReqDto.isMainImage())
                .idx(profileImageReqDto.getIdx())
                .build();

    }

    public ProfileImage addNewImage(String uuid, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .uuid(uuid)
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(profileImageReqDto.isMainImage())
                .idx(profileImageReqDto.getIdx())
                .build();
    }
}
