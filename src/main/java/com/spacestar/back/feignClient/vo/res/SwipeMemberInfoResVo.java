package com.spacestar.back.feignClient.vo.res;

import lombok.Getter;

@Getter
public class SwipeMemberInfoResVo {
    private String uuid;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;

    @Override
    public String toString() {
        return "SwipeMemberInfoResVo{" +
                "uuid='" + uuid + '\'' +
                ", gamePreferenceId=" + gamePreferenceId +
                ", mbtiId=" + mbtiId +
                ", mainGameId=" + mainGameId +
                ", reportCount=" + reportCount +
                '}';
    }
}
