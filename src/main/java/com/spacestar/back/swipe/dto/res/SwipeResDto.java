package com.spacestar.back.swipe.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwipeResDto {
    private long totalMemberCount;
    private int nowPage;
    private List<String> memberUuidList;
    private boolean isLast;
}
