package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.service.QuickMatchingService;
import com.spacestar.back.quickmatching.vo.QuickMatchingEnterReqVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/api/v1/quick-matching")
@RequiredArgsConstructor
public class QuickMatchingController {
    private final QuickMatchingService quickMatchingService;
    private final ModelMapper mapper;
    @PostMapping
    public ResponseEntity<Void> enterQuickMatching(@RequestHeader("UUID")String uuid,
                                                   @RequestBody QuickMatchingEnterReqVo reqVo){
        quickMatchingService.enterQuickMatching(uuid, mapper.map(reqVo, QuickMatchingEnterReqDto.class));
        return null;
    }

    @GetMapping(value="/connect", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestParam("gameName") String gameName){
        SseEmitter emitter = quickMatchingService.connect(gameName);
        return ResponseEntity.ok(emitter);
    }

    @PatchMapping("/accept")
    public void acceptQuickMatch(@RequestHeader("UUID")String uuid) {
        quickMatchingService.acceptQuickMatch(uuid);
    }
    @PatchMapping("/reject")
    public void rejectQuickMatch(@RequestHeader("UUID")String uuid) {
        quickMatchingService.rejectQuickMatch(uuid);
    }


}
