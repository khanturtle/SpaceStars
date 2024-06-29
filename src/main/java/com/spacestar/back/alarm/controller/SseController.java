package com.spacestar.back.alarm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.global.ResponseSuccess;
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
	public Flux<Message> connectSse(@RequestHeader("UUID") String uuid) {
		return alarmService.connectToSse(uuid);
	}

	@DeleteMapping
	@CrossOrigin(origins = "http://localhost:3000")
	@Operation(summary = "SSE 연결 종료")
	public ResponseEntity<Void> disconnectSse(@RequestHeader("UUID") String uuid){

		alarmService.disconnectSse(uuid);
		return new ResponseEntity<>(ResponseSuccess.SSE_DISCONNECT_SUCCESS);
	}
}
