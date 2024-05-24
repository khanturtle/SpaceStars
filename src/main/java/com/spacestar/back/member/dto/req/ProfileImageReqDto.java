package com.spacestar.back.member.dto.req;

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
}
