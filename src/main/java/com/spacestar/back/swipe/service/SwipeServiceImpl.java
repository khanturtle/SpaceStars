package com.spacestar.back.swipe.service;

import com.spacestar.back.swipe.domain.Swipe;
import com.spacestar.back.swipe.dto.SwipeReqDto;
import com.spacestar.back.swipe.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwipeServiceImpl implements SwipeService{
    private final SwipeRepository swipeRepository;

    @Override
    public void addSwipe(SwipeReqDto swipeReqDto, String uuid) {
        swipeRepository.save(Swipe.toEntity(swipeReqDto,uuid));
    }
}
