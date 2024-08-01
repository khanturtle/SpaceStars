package com.spacestar.back.alarm.service;

import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.dto.req.AlarmDeleteReqDto;
import com.spacestar.back.alarm.dto.req.AlarmModifyReqDto;
import com.spacestar.back.alarm.dto.res.AlarmListResDto;
import com.spacestar.back.alarm.dto.res.AlarmStateResDto;
import com.spacestar.back.kafka.message.MatchingMessage;
import com.spacestar.back.kafka.message.Message;

import reactor.core.publisher.Flux;

public interface AlarmService {

	//실시간 알림 스트림 처리
	Flux<Message> connectToSse(String uuid);

	//sse 연결 종료
	void disconnectSse(String uuid);

	//알림 추가
	void addAlarm(String uuid, AlarmAddReqDto alarmAddReqDto);

	//알림 리스트 조회
	AlarmListResDto getAlarmList(String uuid);

	//알림 상태 조회
	AlarmStateResDto getAlarmState(String uuid, String id);

	//알림 상태 수정 -> 읽음 상태로 변경
	void modifyAlarmRead(String uuid, AlarmModifyReqDto alarmModifyReqDto);

	//알림 삭제
	void deleteAlarm(String uuid, AlarmDeleteReqDto alarmDeleteReqDto);
}
