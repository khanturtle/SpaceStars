package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.domain.Swipe;
import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import com.spacestar.back.swipe.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwipeServiceImpl implements SwipeService{
    private final SwipeRepository swipeRepository;

    @Override
    public void addSwipe(SwipeReqDto swipeReqDto, String uuid) {
        swipeRepository.save(Swipe.toEntity(swipeReqDto,uuid));
    }

    @Override
    public List<SwipeListResDto> getSwipe(String uuid) {
        return swipeRepository.findWaitRequest(uuid);
    }

    @Override
    public void agreeSwipe(String uuid) {
        swipeRepository.agreeRequest(uuid);
    }

    @Override
    public void rejectSwipe(String uuid) {
        swipeRepository.rejectRequest(uuid);
    }
}
