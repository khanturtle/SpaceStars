package com.spacestar.back.profile.vo.res;

import lombok.Getter;

@Getter
public class ProfileInfoResVo {

    private String nickname;
    private String introduce;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long exp;
    private Integer reportCount;
    private Boolean swipe;

}
