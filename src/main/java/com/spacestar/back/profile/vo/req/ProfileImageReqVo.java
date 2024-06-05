package com.spacestar.back.profile.vo.req;

import lombok.Getter;

@Getter
public class ProfileImageReqVo {

    private String profileImageUrl;
    private boolean mainImage;
    private int idx;
}
