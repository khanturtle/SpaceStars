package com.spacestar.back.alarm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spacestar.back.alarm.domain.Alarm;

public interface AlarmMongoRepository extends MongoRepository<Alarm, String> {

	// 모든 알림 조회
	List<Alarm> findByReceiverUuid(String receiverUuid);

	// 알림 상태 조회
	Optional<Alarm> findByReceiverUuidAndId(String uuid, String id);
}
