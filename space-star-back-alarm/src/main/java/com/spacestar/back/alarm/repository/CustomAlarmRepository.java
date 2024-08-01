package com.spacestar.back.alarm.repository;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.spacestar.back.alarm.dto.req.AlarmDeleteReqDto;
import com.spacestar.back.alarm.dto.req.AlarmModifyReqDto;

public interface CustomAlarmRepository {
	
	//알림 상태 변경
	UpdateResult modifyAlarm(String uuid, AlarmModifyReqDto alarmModifyReqDto);

	//알림 삭제
	DeleteResult deleteManyAlarm(String uuid, AlarmDeleteReqDto alarmDeleteReqDto);
}
