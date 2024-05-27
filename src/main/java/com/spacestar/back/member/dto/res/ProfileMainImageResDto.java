package com.spacestar.back.member.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileMainImageResDto {

    private String profileImageUrl;
    private boolean mainImage;
    private int imageIdx;

}
