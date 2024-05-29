package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;

import java.util.List;

public interface SwipeService {

    void addSwipe(SwipeReqDto swipeReqDto, String uuid);

    List<SwipeListResDto> getReceivedSwipe(String uuid);

    List<SwipeListResDto> getSentSwipe(String uuid);

    void agreeSwipe(String uuid);

    void rejectSwipe(String uuid);

    void deleteExpiredSwipe();
}
