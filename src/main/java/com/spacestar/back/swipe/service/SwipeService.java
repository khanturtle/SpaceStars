package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.dto.SwipeReqDto;
import org.springframework.data.domain.Pageable;

public interface SwipeService {

        void addSwipe(SwipeReqDto swipeReqDto,String uuid);
}
