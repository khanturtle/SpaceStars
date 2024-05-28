package com.spacestar.back.swipe.dto.res;

import com.spacestar.back.swipe.domain.Swipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwipeListResDto {
    private String matchMember;
}
