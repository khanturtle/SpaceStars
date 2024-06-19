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
    private boolean main;


    public static ProfileImage addNewImage(String uuid, boolean b, String profileImageUrl) {
        return ProfileImage.builder()
                .uuid(uuid)
                .profileImageUrl(profileImageUrl)
                .main(b)
                .build();
    }


    public static ProfileImage updateImage(String uuid, Long id, boolean b, String profileImageUrl) {
        return ProfileImage.builder()
                .id(id)
                .uuid(uuid)
                .profileImageUrl(profileImageUrl)
                .main(b)
                .build();
    }
}
