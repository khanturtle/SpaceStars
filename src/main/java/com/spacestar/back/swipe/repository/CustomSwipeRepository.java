package com.spacestar.back.swipe.repository;

import com.spacestar.back.swipe.dto.res.SwipeListResDto;

import java.util.List;

public interface CustomSwipeRepository {
    List<SwipeListResDto> findWaitRequest(String uuid);

    List<SwipeListResDto> findSentRequest(String uuid);

    void agreeRequest(String uuid);

    void rejectRequest(String uuid);

    void deleteExpiredSwipe();
}
