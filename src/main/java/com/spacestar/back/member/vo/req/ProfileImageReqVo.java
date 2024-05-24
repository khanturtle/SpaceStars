package com.spacestar.back.member.vo.req;

import lombok.Getter;

@Getter
public class ProfileImageReqVo {

    private String profileImageUrl;
    private boolean mainImage;
    private int idx;
}
