package com.spacestar.back.alarm.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;
import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomAlarmRepositoryImpl implements CustomAlarmRepository {

	private final MongoTemplate mongoTemplate;

	// 알림상태를 읽음으로 업데이트
	@Override
	public UpdateResult modifyAlarm(String alarmId, String uuid) {

		return mongoTemplate.updateFirst(
			new Query(new Criteria().andOperator(
				Criteria.where("_id").is(new ObjectId(alarmId)),
				Criteria.where("receiverUuid").is(uuid),
				Criteria.where("checkStatus").is("UNREAD")
			)),
			new Update().set("checkStatus", "READ"),
			Alarm.class
		);
	}
}
