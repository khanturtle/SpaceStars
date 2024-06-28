package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeCountResDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import com.spacestar.back.swipe.dto.res.SwipeResDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SwipeService {

    void addSwipe(SwipeReqDto swipeReqDto, String uuid);

    List<SwipeListResDto> getReceivedSwipe(String uuid);

    List<SwipeListResDto> getSentSwipe(String uuid);

    void agreeSwipe(String uuid, String fromMemberUuid);

    void rejectSwipe(String uuid, String fromMemberUuid);

    void deleteExpiredSwipe();

    SwipeCountResDto countSwipe(String uuid);

    SwipeResDto getSwipeMembersAi(String uuid, Pageable pageable);
    SwipeResDto getSwipeMembers(String uuid, Pageable pageable);

}
