package com.spacestar.back.profile.dto.req;

import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.vo.req.ProfilePlayGameInfoReqVo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileInfoReqDto {

    private String introduction;
    private Long mbtiId;
    private boolean swipe;

    public static Profile updateProfileInfo(Profile profile, ProfileInfoReqDto profileInfoReqDto) {

        return Profile.builder()
                .id(profile.getId())
                .uuid(profile.getUuid())
                .introduce(profileInfoReqDto.getIntroduction())
                .gamePreferenceId(profile.getGamePreferenceId())
                .mbtiId(profileInfoReqDto.getMbtiId())
                .exp(profile.getExp())
                .reportCount(profile.getReportCount())
                .swipe(profileInfoReqDto.isSwipe())
                .build();
    }
}
