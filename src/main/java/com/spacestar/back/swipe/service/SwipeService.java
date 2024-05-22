package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import com.spacestar.back.swipe.vo.res.SwipeListResVo;

import java.util.List;

public interface SwipeService {

        void addSwipe(SwipeReqDto swipeReqDto,String uuid);

        List<SwipeListResDto> getSwipe(String uuid);

        void agreeSwipe(String uuid);
}
