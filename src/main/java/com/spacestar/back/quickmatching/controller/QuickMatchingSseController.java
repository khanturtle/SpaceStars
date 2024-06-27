package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.quickmatching.service.QuickMatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
@Tag(name = "Queue", description = "빠른 매칭(큐)")
@RequestMapping("/api/v1/sse-quick")
@RequiredArgsConstructor
public class QuickMatchingSseController {
    private final QuickMatchingService quickMatchingService;

    @Operation(summary = "대기 큐 SSE 연결")
    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(@RequestHeader("UUID") String uuid) {
        quickMatchingService.connect(uuid);

        SseEmitter emitter = new SseEmitter();

        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();

        String message = quickMatchingService.getStrings();

        sseMvcExecutor.execute(() -> {
            try {
                while (true) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data(message);
                    emitter.send(event);
                    Thread.sleep(3000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return ResponseEntity.ok(emitter);
    }
}
