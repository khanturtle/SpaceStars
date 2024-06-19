package com.spacestar.back.alarm.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.alarm.vo.req.AlarmAddReqVo;
import com.spacestar.back.alarm.vo.res.AlarmListResVo;
import com.spacestar.back.alarm.vo.res.AlarmStateResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.kafka.message.MatchingMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarm")
@Tag(name = "Alarm", description = "알림")
public class AlarmController {

	private final AlarmServiceImpl alarmService;
	private final ModelMapper modelMapper;
	private final Sinks.Many<MatchingMessage> sink;

	@PostMapping
	@Operation(summary = "알림 생성")
	public ResponseEntity<Void> addAlarm(@RequestHeader("UUID") String uuid,
										@RequestBody AlarmAddReqVo alarmAddReqVo){
		alarmService.addAlarm(uuid, modelMapper.map(alarmAddReqVo, AlarmAddReqDto.class));
		return new ResponseEntity<>(ResponseSuccess.ALARM_INSERT_SUCCESS);
	}
	//알림 리스트 조회 API
	@GetMapping("/list")
	@Operation(summary = "알림 목록 조회")
	public ResponseEntity<AlarmListResVo> getAlarmList(@RequestHeader("UUID") String uuid){

		return new ResponseEntity<>(ResponseSuccess.ALARM_LIST_SELECT_SUCCESS,
			modelMapper.map(alarmService.getAlarmList(uuid), AlarmListResVo.class));
	}

	// 매칭 알림 실시간 수신
	@GetMapping(value ="/stream-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@Operation(summary = "실시간 알림 SSE 입장")
	public Flux<MatchingMessage> matchingEvents(@RequestHeader("UUID") String uuid){
		log.info("Received UUID: {}", uuid);
		return sink.asFlux().filter(message -> uuid.equals(message.getReceiverUuid()));
	}

	@GetMapping(value = "/state/{id}")
	@Operation(summary = "알림 상태 조회")
	public ResponseEntity<AlarmStateResVo> getAlarmState(@RequestHeader("UUID") String uuid,
			@PathVariable("id") String id){
		return new ResponseEntity<>(ResponseSuccess.ALARM_STATE_SELECT_SUCCESS,
				modelMapper.map(alarmService.getAlarmState(uuid, id), AlarmStateResVo.class));
	}

	//Todo
	//알림 전송 API

	//Todo
	//알림 수락 API

	//Todo
	//알림 거절 API
}
