package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.service.QuickMatchingService;
import com.spacestar.back.quickmatching.vo.QuickMatchingEnterReqVo;
import com.spacestar.back.quickmatching.vo.QuickMatchingResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@Tag(name = "Queue", description = "빠른 매칭(큐)")
@RequestMapping("/api/v1/quick-matching")
@RequiredArgsConstructor
public class QuickMatchingController {
    private final QuickMatchingService quickMatchingService;
    private final ModelMapper mapper;
    @Operation(summary = "대기 큐 진입")
    @PostMapping
    public ResponseEntity<Void> enterQuickMatching(@RequestHeader("UUID")String uuid,
                                                   @RequestBody QuickMatchingEnterReqVo reqVo){
        quickMatchingService.enterQuickMatching(uuid, mapper.map(reqVo, QuickMatchingEnterReqDto.class));
        return null;
    }
    @Operation(summary = "대기 큐 SSE 연결")
    @GetMapping(value="/connect", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestParam("gameName") String gameName){
        SseEmitter emitter = quickMatchingService.connect(gameName);
        return ResponseEntity.ok(emitter);
    }
    @Operation(summary = "큐 요청 수락하기")
    @PatchMapping("/accept")
    public void acceptQuickMatch(@RequestHeader("UUID")String uuid) {
        quickMatchingService.acceptQuickMatch(uuid);
    }
    @Operation(summary = "큐 요청 거절하기")
    @PatchMapping("/reject")
    public void rejectQuickMatch(@RequestHeader("UUID")String uuid) {
        quickMatchingService.rejectQuickMatch(uuid);
    }
    @Operation(summary = "수락 큐 결과")
    @PostMapping("/complete")
    public QuickMatchingResVo completeQuickMatch(@RequestHeader("UUID")String uuid,
                                                 @RequestBody QuickMatchingEnterReqVo reqVo){
        return mapper.map(quickMatchingService.completeQuickMatch(uuid,mapper.map(reqVo, QuickMatchingEnterReqDto.class)), QuickMatchingResVo.class);
    }

}
