package com.spacestar.back.alarm.repository;

import com.mongodb.client.result.UpdateResult;

public interface CustomAlarmRepository {

	UpdateResult modifyAlarm(String alarmId, String uuid);
}
