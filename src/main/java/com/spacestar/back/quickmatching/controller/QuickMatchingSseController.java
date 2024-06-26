package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.quickmatching.dto.req.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.service.QuickMatchingService;
import com.spacestar.back.quickmatching.vo.req.QuickMatchingEnterReqVo;
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
public class QuickMatchingSseController {
    private final QuickMatchingService quickMatchingService;
    private final ModelMapper mapper;

    @Operation(summary = "대기 큐 SSE 연결")
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestHeader("UUID") String uuid,
                                              @RequestBody QuickMatchingEnterReqVo reqVo) {
        SseEmitter emitter = quickMatchingService.connect(mapper.map(reqVo, QuickMatchingEnterReqDto.class), uuid);
        return ResponseEntity.ok(emitter);
    }
}
