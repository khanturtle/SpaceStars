package com.spacestar.back.alarm.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.dto.AlarmListResDto;
import com.spacestar.back.alarm.dto.AlarmResDto;
import com.spacestar.back.alarm.repository.AlarmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

	private final AlarmRepository alarmRepository;

	// 알림 리스트 조회
	@Override
	public AlarmListResDto getAlarmList(String uuid) {

		List<Alarm> alarms = alarmRepository.findByReceiverUuid(uuid);

		// IntStream을 사용하여 인덱스와 함께 AlarmResDto 리스트를 생성합니다.
		List<AlarmResDto> alarmResDtos = IntStream.range(0, alarms.size())
			.mapToObj(i -> {
				Alarm alarm = alarms.get(i);
				return AlarmResDto.builder()
					.index(i)
					.senderUuid(alarm.getSenderUuid())
					.createdAt(alarm.getCreatedAt())
					.checkStatus(alarm.getCheckStatus())
					.build();
			})
			.collect(Collectors.toList());

		// AlarmResDto 리스트를 AlarmListResDto 객체에 담습니다.
		return AlarmListResDto.builder()
			.alarmList(alarmResDtos)
			.build();
	}
}
