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
public class SwipeServiceImpl implements SwipeService {
    private final SwipeRepository swipeRepository;

    @Override
    public void addSwipe(SwipeReqDto swipeReqDto, String uuid) {
        swipeRepository.save(Swipe.toEntity(swipeReqDto, uuid));
        //Todo 요청을 보내면 추천인 목록에서 제외 시켜야함 (거절시 처럼 Redis에 저장해야할듯?)
    }

    @Override
    public List<SwipeListResDto> getReceivedSwipe(String uuid) {
        return swipeRepository.findWaitRequest(uuid);
    }

    @Override
    public List<SwipeListResDto> getSentSwipe(String uuid) {
        return swipeRepository.findByMatchFromMember(uuid).stream().map(swipe -> SwipeListResDto.builder()
                .matchMember(swipe.getMatchToMember())
                .build())
                .toList();
    }

    @Override
    public void agreeSwipe(String uuid) {
        swipeRepository.agreeRequest(uuid);
        //Todo 수락시 채팅방 생성되어야 함
    }

    @Override
    public void rejectSwipe(String uuid) {
        swipeRepository.rejectRequest(uuid);
        //Todo 거절시 Redis에 해당 유저 저장해야함
    }
}
