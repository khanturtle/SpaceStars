package com.spacestar.back.alarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.dto.AlarmListResDto;

public interface AlarmRepository extends MongoRepository<Alarm, String>, CustomAlarmRepository {

	List<Alarm> findByReceiverUuid(String receiverUuid);
}
