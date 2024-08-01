package com.spacestar.back.swipe.vo.res;

import lombok.Getter;

import java.util.List;

@Getter
public class SwipeResVo {
    private long totalMemberCount;
    private int nowPage;
    private List<String> memberUuidList;
    private boolean isLast;
    private int lastPage;
}
