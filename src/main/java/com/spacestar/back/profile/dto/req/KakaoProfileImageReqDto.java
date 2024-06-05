package com.spacestar.back.profile.dto.req;

import com.spacestar.back.profile.domain.ProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoProfileImageReqDto {


    private String profileImageUrl;



    public ProfileImage addNewImage(String uuid, KakaoProfileImageReqDto kakaoProfileImageReqDto) {
        return ProfileImage.builder()
                .uuid(uuid)
                .profileImageUrl(kakaoProfileImageReqDto.getProfileImageUrl())
                .main(true)
                .idx(0)
                .build();
    }
}
