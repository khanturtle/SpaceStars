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

    public static ProfileImage updateImage(String uuid, boolean b, Long id, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .id(id)
                .uuid(uuid)
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .build();

    }

    public static ProfileImage addNewImage(String uuid, boolean b, ProfileImageReqDto profileImageReqDto) {
        return ProfileImage.builder()
                .uuid(uuid)
                .profileImageUrl(profileImageReqDto.getProfileImageUrl())
                .main(b)
                .build();
    }


}
