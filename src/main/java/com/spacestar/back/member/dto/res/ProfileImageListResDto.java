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

    private int index;
    private String profileImageUrl;
    private boolean mainImage;
    private int imageIdx;

    public static ProfileImageListResDto convertToDto(int i, ProfileImage profileImage) {

        return ProfileImageListResDto.builder()
                .index(i)
                .profileImageUrl(profileImage.getProfileImageUrl())
                .mainImage(profileImage.isMain())
                .imageIdx(profileImage.getIdx())
                .build();
    }
}
