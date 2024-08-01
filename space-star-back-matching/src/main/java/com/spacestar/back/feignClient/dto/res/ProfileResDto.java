package com.spacestar.back.feignClient.dto.res;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProfileResDto {
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long mainGameId;
    private int reportCount;
}
