package com.spacestar.back.alarm.service;

import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.dto.res.AlarmListResDto;
import com.spacestar.back.alarm.dto.res.AlarmStateResDto;

public interface AlarmService {

	// 알림 추가
	void addAlarm(String uuid, AlarmAddReqDto alarmAddReqDto);

	// 알림 리스트 조회
	AlarmListResDto getAlarmList(String uuid);

	//알림 상태 조회
	AlarmStateResDto getAlarmState(String uuid, String id);

	//알림 상태 수정 -> 읽음 상태로 변경
	void modifyAlarmRead(String alarmId, String uuid);

	//Todo
	// 알림 삭제
}
