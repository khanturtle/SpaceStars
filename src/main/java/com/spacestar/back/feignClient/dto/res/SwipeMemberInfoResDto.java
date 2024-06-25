package com.spacestar.back.feignClient.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SwipeMemberInfoResDto {
    private String uuid;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;

    public static SwipeMemberInfoResDto toDto(ProfileResDto resDto, String uuid){
        return SwipeMemberInfoResDto.builder()
                .uuid(uuid)
                .gamePreferenceId(resDto.getGamePreferenceId())
                .mbtiId(resDto.getMbtiId())
                .mainGameId(resDto.getMainGameId())
                .reportCount(resDto.getReportCount())
                .build();
    }
}
