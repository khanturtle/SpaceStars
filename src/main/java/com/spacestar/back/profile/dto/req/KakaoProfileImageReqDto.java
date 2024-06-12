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

}
