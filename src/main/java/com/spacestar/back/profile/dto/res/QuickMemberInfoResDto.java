package com.spacestar.back.profile.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuickMemberInfoResDto {

    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;

    public static QuickMemberInfoResDto converter(Long gamePreferenceId, Long mbtiId, Long mainGameId, int reportCount) {
        return QuickMemberInfoResDto.builder()
                .gamePreferenceId(gamePreferenceId)
                .mbtiId(mbtiId)
                .mainGameId(mainGameId)
                .reportCount(reportCount)
                .build();
    }
}
