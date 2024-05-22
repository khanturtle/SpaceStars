package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.dto.req.SwipeReqDto;

public interface SwipeService {

        void addSwipe(SwipeReqDto swipeReqDto,String uuid);
}
