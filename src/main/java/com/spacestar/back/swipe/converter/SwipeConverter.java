package com.spacestar.back.swipe.converter;

import com.spacestar.back.swipe.SwipeStatus;
import com.spacestar.back.swipe.domain.Swipe;
import com.spacestar.back.swipe.dto.req.SwipeReqDto;

public class SwipeConverter {
    public static Swipe toEntity(SwipeReqDto swipeReqDto, String uuid){
        return Swipe.builder()
                .matchFromMember(uuid)
                .matchToMember(swipeReqDto.getMatchToMember())
                .memo(swipeReqDto.getMemo())
                .status(SwipeStatus.WAIT)
                .build();
    }
}
