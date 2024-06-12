package com.spacestar.back.alarm.service;

import org.springframework.stereotype.Service;

import com.spacestar.back.alarm.dto.AlarmListResDto;
import com.spacestar.back.alarm.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

	private final AlarmRepository alarmRepository;

	// 알림 리스트 조회
	@Override
	public AlarmListResDto getAlarmList(String uuid){
		return AlarmListResDto.builder()
			.alarmList(alarmRepository.findAlarmList(uuid))
			.build();
	}
}
