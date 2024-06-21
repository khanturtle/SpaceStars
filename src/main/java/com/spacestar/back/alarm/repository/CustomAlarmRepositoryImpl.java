package com.spacestar.back.alarm.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.spacestar.back.alarm.domain.Alarm;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomAlarmRepositoryImpl implements CustomAlarmRepository {

	private final MongoTemplate mongoTemplate;
	
	// 알림상태를 읽음으로 업데이트
	@Override
	public void modifyAlarm(String alarmId){

		mongoTemplate.updateFirst(
			new Query(Criteria.where("id").is(alarmId)),
			new Update().set("checkStatus", "READ"),
			Alarm.class
		);
	}
}
