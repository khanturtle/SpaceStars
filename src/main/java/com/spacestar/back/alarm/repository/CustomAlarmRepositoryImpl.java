package com.spacestar.back.alarm.repository;

import static com.spacestar.back.alarm.domain.QAlarm.*;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.alarm.domain.QAlarm;
import com.spacestar.back.alarm.dto.AlarmResDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAlarmRepositoryImpl implements CustomAlarmRepository{

	private final JPAQueryFactory query;

	@Override
	public List<AlarmResDto> findAlarmList(String uuid){

		List<AlarmResDto> alarms = query
			.select(Projections.constructor(AlarmResDto.class,
				Expressions.asNumber(0).as("index"),
				alarm.senderUuid,
				alarm.createdAt,
				alarm.checkStatus))
			.from(alarm)
			.where(alarm.receiverUuid.eq(uuid))
			.fetch();

		// index 부여
		return IntStream.range(0, alarms.size())
			.mapToObj(i -> AlarmResDto.builder()
				.index(i)
				.senderUuid(alarms.get(i).getSenderUuid())
				.createdAt(alarms.get(i).getCreatedAt())
				.checkStatus(alarms.get(i).getCheckStatus())
				.build())
				.toList();
	}
}
