package com.spacestar.back.profile.dto.res;

import com.spacestar.back.profile.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileInfoResDto {

    private String introduce;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long exp;
    private int reportCount;
    private boolean swipe;

    public static ProfileInfoResDto toDto(Profile profile) {

        return ProfileInfoResDto.builder()
                .introduce(profile.getIntroduce())
                .gamePreferenceId(profile.getGamePreferenceId())
                .mbtiId(profile.getMbtiId())
                .exp(profile.getExp())
                .reportCount(profile.getReportCount())
                .swipe(profile.isSwipe())
                .build();
    }
}
