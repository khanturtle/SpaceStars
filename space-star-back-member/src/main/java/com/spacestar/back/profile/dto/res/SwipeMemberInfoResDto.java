package com.spacestar.back.profile.dto.res;

import com.spacestar.back.profile.domain.PlayGame;
import com.spacestar.back.profile.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SwipeMemberInfoResDto {

    private String uuid;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;

    public static SwipeMemberInfoResDto toDto(Profile profile, Long mainGameId){
        return SwipeMemberInfoResDto.builder()
                .uuid(profile.getUuid())
                .gamePreferenceId(profile.getGamePreferenceId())
                .mainGameId(profile.getMbtiId())
                .mainGameId(mainGameId)
                .reportCount(profile.getReportCount())
                .build();
    }
}

