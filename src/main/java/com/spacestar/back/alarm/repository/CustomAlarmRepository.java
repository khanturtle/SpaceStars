package com.spacestar.back.alarm.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.spacestar.back.alarm.dto.req.AlarmDeleteReqDto;

public interface CustomAlarmRepository {
	
	//알림 읽음 상태로 변경
	UpdateResult modifyAlarm(String alarmId, String uuid);

	//알림 삭제
	DeleteResult deleteManyAlarm(String uuid, AlarmDeleteReqDto alarmDeleteReqDto);
}
