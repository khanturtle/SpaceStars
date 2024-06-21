package com.spacestar.back.swipe.service;

import com.spacestar.back.feignClient.service.FeignClientService;
import com.spacestar.back.kafka.message.MatchingMessage;
import com.spacestar.back.kafka.service.KafkaService;
import com.spacestar.back.swipe.converter.SwipeConverter;
import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeCountResDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import com.spacestar.back.swipe.dto.res.SwipeResDto;
import com.spacestar.back.swipe.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwipeServiceImpl implements SwipeService {
    private final SwipeRepository swipeRepository;
    private final KafkaService kafkaService;
    private final FeignClientService feignClientService;

    @Override
    public void addSwipe(SwipeReqDto swipeReqDto, String uuid) {
        kafkaService.sendMessage(MatchingMessage.toMatchingMessage(uuid, swipeReqDto));
        swipeRepository.save(SwipeConverter.toEntity(swipeReqDto, uuid));
        //Todo 요청을 보내면 추천인 목록에서 제외 시켜야함 (거절시 처럼 Redis에 저장해야할듯?)
    }

    //받은 요청 조회
    @Override
    public List<SwipeListResDto> getReceivedSwipe(String uuid) {
        return swipeRepository.findWaitRequest(uuid);
    }
    //보낸 요청 조회
    @Override
    public List<SwipeListResDto> getSentSwipe(String uuid) {
        return swipeRepository.findSentRequest(uuid);
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

    @Override
    public void deleteExpiredSwipe() {
        swipeRepository.deleteExpiredSwipe();
    }
  
    @Override
    public SwipeCountResDto countSwipe(String uuid) {
        return SwipeCountResDto.builder()
                .count(swipeRepository.countSwipe(uuid))
                .build();
    }

    @Override
    public List<SwipeResDto> getSwipeMembers(String uuid) {
        //todo 아래값 리스트로
        String response = feignClientService.getOpenAi(uuid);
        String[] tokens = response.substring(1, response.length() - 1).split(",\\s*");

        // List<SwipeResDto>를 담을 리스트 생성
        List<SwipeResDto> swipeResDtoList = new ArrayList<>();

        // 각 토큰을 SwipeResDto 객체로 변환하여 리스트에 추가
        for (String token : tokens) {
            SwipeResDto swipeResDto = new SwipeResDto();
            swipeResDto.setMemberUuid(token.trim()); // 공백 제거 후 설정
            swipeResDtoList.add(swipeResDto);
        }

        return swipeResDtoList;
    }
}
