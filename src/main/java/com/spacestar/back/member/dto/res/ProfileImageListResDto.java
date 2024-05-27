package com.spacestar.back.member.dto.res;

import com.spacestar.back.member.domain.ProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileImageListResDto {

    private String profileImageUrl;
    private boolean mainImage;
    private int idx;

    public static ProfileImageListResDto convertToDto(ProfileImage profileImage) {

        return ProfileImageListResDto.builder()
                .profileImageUrl(profileImage.getProfileImageUrl())
                .mainImage(profileImage.isMain())
                .idx(profileImage.getIdx())
                .build();
    }
}
