package com.spacestar.back.alarm.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.dto.res.AlarmListResDto;
import com.spacestar.back.alarm.dto.res.AlarmResDto;
import com.spacestar.back.alarm.dto.res.AlarmStateResDto;
import com.spacestar.back.alarm.repository.AlarmMongoRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

	private final AlarmMongoRepository alarmRepository;

	// 알림 추가
	@Override
	public void addAlarm(String uuid, AlarmAddReqDto alarmAddReqDto){
		alarmRepository.save(AlarmAddReqDto.toEntity(uuid, alarmAddReqDto));
	}
	// 알림 리스트 조회
	@Override
	public AlarmListResDto getAlarmList(String uuid) {

		List<Alarm> alarms = alarmRepository.findByReceiverUuid(uuid);
		return AlarmListResDto.builder()
				.alarmList(IntStream.range(0, alarms.size())
						.mapToObj(i -> AlarmResDto.builder()
								.index(i)
								.senderUuid(alarms.get(i).getSenderUuid())
								.checkStatus(alarms.get(i).getCheckStatus())
								.alarmType(alarms.get(i).getAlarmType())
								.content(alarms.get(i).getContent())
								.build()).toList())
				.build();
	}
	
	// 알림 상태 조회
	@Override
	public AlarmStateResDto getAlarmState(String uuid, String id){
		Alarm alarm = alarmRepository.findByReceiverUuidAndId(uuid, id)
				.orElseThrow(()-> new GlobalException(ResponseStatus.NOT_EXIST_ALARM));

		return AlarmStateResDto.builder()
				.checkStatus(alarm.getCheckStatus())
				.build();
	}
}
