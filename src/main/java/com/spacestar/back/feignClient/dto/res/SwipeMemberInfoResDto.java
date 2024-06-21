package com.spacestar.back.feignClient.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

    @Override
    public String toString() {
        return "SwipeMemberInfoResDto{" +
                "uuid='" + uuid + '\'' +
                ", gamePreferenceId=" + gamePreferenceId +
                ", mbtiId=" + mbtiId +
                ", mainGameId=" + mainGameId +
                ", reportCount=" + reportCount +
                '}';
    }
}
