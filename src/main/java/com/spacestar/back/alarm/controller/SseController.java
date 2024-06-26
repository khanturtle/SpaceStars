package com.spacestar.back.alarm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.kafka.message.Message;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sse")
@Tag(name = "Alarm-SSE", description = "SSE 연결")
public class SseController {

	private final AlarmServiceImpl alarmService;
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@CrossOrigin(origins = "http://localhost:3000")
	@Operation(summary = "실시간 알림 SSE 입장")
	public Flux<Message> matchingEvents(@RequestHeader("UUID") String uuid) {
		log.info("연결시도");
		return alarmService.streamAlarms(uuid);
	}
}
