package com.spacestar.back.alarm.service;

import com.spacestar.back.alarm.dto.res.AlarmListResDto;

public interface AlarmService {

	// 알림 리스트 조회
	AlarmListResDto getAlarmList(String uuid);

	// 알림 추가
	void addAlarm()
}
