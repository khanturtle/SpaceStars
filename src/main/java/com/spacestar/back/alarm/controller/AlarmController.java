package com.spacestar.back.alarm.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.alarm.vo.AlarmListResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarm")
@Tag(name = "Alarm", description = "알림")
public class AlarmController {

	private final AlarmServiceImpl alarmService;
	private final ModelMapper modelMapper;

	//알림 리스트 조회 API
	@GetMapping("/")
	@Operation(summary = "알림 목록 조회")
	public ResponseEntity<AlarmListResVo> getAlarmList(@RequestHeader("UUID") String uuid){

		return new ResponseEntity<>(ResponseSuccess.ALARM_LIST_SELECT_SUCCESS,
			modelMapper.map(alarmService.getAlarmList(uuid), AlarmListResVo.class));
	}

	//Todo
	//알림 상태 조회 API

	//Todo
	//알림 전송 API

	//Todo
	//알림 수락 API

	//Todo
	//알림 거절 API
}
