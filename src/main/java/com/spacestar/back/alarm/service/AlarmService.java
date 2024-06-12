package com.spacestar.back.alarm.service;

import com.spacestar.back.alarm.dto.AlarmListResDto;

public interface AlarmService {

	// 알림 리스트 조회
	AlarmListResDto getAlarmList(String uuid);
}
