package com.spacestar.back.feignClient.vo.res;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SwipeMemberInfoResVo {
    private String uuid;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;
}
