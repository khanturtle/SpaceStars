package com.spacestar.back.swipe.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.swipe.dto.req.SwipeReqDto;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import com.spacestar.back.swipe.dto.res.SwipeResDto;
import com.spacestar.back.swipe.service.SwipeService;
import com.spacestar.back.swipe.vo.req.SwipeReqVo;
import com.spacestar.back.swipe.vo.res.SwipeCountResVo;
import com.spacestar.back.swipe.vo.res.SwipeListResVo;
import com.spacestar.back.swipe.vo.res.SwipeResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Swipe", description = "스와이프")
@RequiredArgsConstructor
@RequestMapping("/api/v1/swipe")
public class SwipeController {
    private final SwipeService swipeService;
    private final ModelMapper mapper;

    @Operation(summary = "스와이프 사용자 목록 조회 (AI)")
    @GetMapping("/ai")
    public ResponseEntity<SwipeResVo> getSwipeMembersAi(@RequestHeader("UUID") String uuid,
                                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        SwipeResDto swipePage = swipeService.getSwipeMembersAi(uuid, pageable);
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, mapper.map(swipePage, SwipeResVo.class));
    }

    @Operation(summary = "스와이프 사용자 목록 조회")
    @GetMapping
    public ResponseEntity<SwipeResVo> getSwipeMembers(@RequestHeader("UUID") String uuid,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        SwipeResDto swipePage = swipeService.getSwipeMembers(uuid, pageable);
        return new ResponseEntity<>(ResponseSuccess.SUCCESS, mapper.map(swipePage, SwipeResVo.class));
    }

    @Operation(summary = "스와이프 요청 보내기")
    @PostMapping("/add")
    public ResponseEntity<Void> addSwipe(@RequestHeader("UUID") String uuid,
                                         @RequestBody SwipeReqVo swipeReqVo) {
        swipeService.addSwipe(mapper.map(swipeReqVo, SwipeReqDto.class), uuid);
        return new ResponseEntity<>(ResponseSuccess.SWIPE_ADD_SUCCESS);
    }

    @Operation(summary = "받은 요청 조회")
    @GetMapping("/received")
    public ResponseEntity<List<SwipeListResVo>> getSwipe(@RequestHeader("UUID") String uuid) {
        List<SwipeListResDto> swipeListResDtos = swipeService.getReceivedSwipe(uuid);
        List<SwipeListResVo> SwipeListResVos = swipeListResDtos.stream()
                .map(dto -> mapper.map(dto, SwipeListResVo.class))
                .toList();

        return new ResponseEntity<>(ResponseSuccess.SWIPE_GET_SUCCESS, SwipeListResVos);
    }

    @Operation(summary = "보낸 요청 조회")
    @GetMapping("/sent")
    public ResponseEntity<List<SwipeListResVo>> getSentSwipe(@RequestHeader("UUID") String uuid) {
        List<SwipeListResDto> swipeListResDtos = swipeService.getSentSwipe(uuid);
        List<SwipeListResVo> SwipeListResVos = swipeListResDtos.stream()
                .map(dto -> mapper.map(dto, SwipeListResVo.class))
                .toList();

        return new ResponseEntity<>(ResponseSuccess.SWIPE_SENT_GET_SUCCESS, SwipeListResVos);
    }

    @Operation(summary = "요청 수락하기")
    @PatchMapping("/agree")
    public ResponseEntity<Void> agreeSwipe(@RequestHeader("UUID") String uuid) {
        swipeService.agreeSwipe(uuid);
        return new ResponseEntity<>(ResponseSuccess.SWIPE_AGREE_SUCCESS);
    }

    @Operation(summary = "요처 거절하기")
    @PatchMapping("/reject")
    public ResponseEntity<Void> rejectSwipe(@RequestHeader("UUID") String uuid) {
        swipeService.rejectSwipe(uuid);
        return new ResponseEntity<>(ResponseSuccess.SWIPE_REJECT_SUCCESS);
    }

    @Operation(summary = "보낸 요청 횟수 조회")
    @GetMapping("/count")
    public ResponseEntity<SwipeCountResVo> countSwipe(@RequestHeader("UUID") String uuid) {
        SwipeCountResVo swipeCount = mapper.map(swipeService.countSwipe(uuid), SwipeCountResVo.class);
        return new ResponseEntity<>(ResponseSuccess.SWIPE_COUNT_SUCCESS, swipeCount);
    }
}
