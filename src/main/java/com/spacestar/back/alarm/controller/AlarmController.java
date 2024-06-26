package com.spacestar.back.alarm.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.dto.req.AlarmDeleteReqDto;
import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.alarm.vo.req.AlarmAddReqVo;
import com.spacestar.back.alarm.vo.res.AlarmListResVo;
import com.spacestar.back.alarm.vo.res.AlarmStateResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.kafka.message.Message;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/alarm")
@Tag(name = "Alarm", description = "알림")
public class AlarmController {

	private final AlarmServiceImpl alarmService;
	private final ModelMapper modelMapper;

	@PostMapping
	@Operation(summary = "알림 생성")
	public ResponseEntity<Void> addAlarm(@RequestHeader("UUID") String uuid,
		@RequestBody AlarmAddReqVo alarmAddReqVo) {
		alarmService.addAlarm(uuid, modelMapper.map(alarmAddReqVo, AlarmAddReqDto.class));
		return new ResponseEntity<>(ResponseSuccess.ALARM_INSERT_SUCCESS);
	}

	//알림 리스트 조회 API
	@GetMapping("/list")
	@Operation(summary = "알림 목록 조회")
	public ResponseEntity<AlarmListResVo> getAlarmList(@RequestHeader("UUID") String uuid) {

		return new ResponseEntity<>(ResponseSuccess.ALARM_LIST_SELECT_SUCCESS,
			modelMapper.map(alarmService.getAlarmList(uuid), AlarmListResVo.class));
	}

	@GetMapping("/state/{alarmId}")
	@Operation(summary = "알림 상태 조회")
	public ResponseEntity<AlarmStateResVo> getAlarmState(@RequestHeader("UUID") String uuid,
		@PathVariable("alarmId") String alarmId) {
		return new ResponseEntity<>(ResponseSuccess.ALARM_STATE_SELECT_SUCCESS,
			modelMapper.map(alarmService.getAlarmState(uuid, alarmId), AlarmStateResVo.class));
	}

	@PatchMapping("/modify/check-status/{alarmId}")
	@Operation(summary = "알림 상태 수정 (읽음으로 처리)")
	public ResponseEntity<Void> modifyAlarmCheckStatus(@RequestHeader("UUID") String uuid,
		@PathVariable("alarmId") String alarmId) {

		alarmService.modifyAlarmRead(alarmId, uuid);
		return new ResponseEntity<>(ResponseSuccess.ALARM_CHECK_STATE_UPDATE_SUCCESS);
	}

	@DeleteMapping("/delete")
	@Operation(summary = "알림 삭제")
	public ResponseEntity<Void> deleteAlarm(@RequestHeader("UUID") String uuid,
		@RequestBody AlarmDeleteReqDto alarmDeleteReqDto) {
		alarmService.deleteAlarm(uuid, alarmDeleteReqDto);
		return new ResponseEntity<>(ResponseSuccess.ALARM_LIST_DELETE_SUCCESS);
	}
}
