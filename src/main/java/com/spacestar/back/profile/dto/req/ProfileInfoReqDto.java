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
    private Long gamePreferenceId;
    private Long mainGameId;

    private List<Long> likedGameIds;
    private List<ProfilePlayGameInfoReqDto> playGameIds;
    private boolean swipe;

    public Profile updateToEntity(Long id, String uuid, ProfileInfoReqDto profileInfoReqDto) {
        return Profile.builder()
                .id(id)
                .uuid(uuid)
                .introduce(profileInfoReqDto.getIntroduction())
                .mbtiId(profileInfoReqDto.getMbtiId())
                .gamePreferenceId(profileInfoReqDto.getGamePreferenceId())
                .swipe(profileInfoReqDto.isSwipe())
                .build();
    }
}
