package com.spacestar.back.member.vo.res;

import com.spacestar.back.member.enums.GenderType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProfileMatchingResVo {

    private String nickname;
    private String description;
    private GenderType gender;
    private LocalDate birth;
    private List<Long> likedGameIds;
    private List<Long> playGameIds;
    private Long mainGameId;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long exp;

    //프로필 상세
    private String email;
    private int reportCount;
    private boolean swipe;
    private LocalDateTime profileUpdateDate;
}