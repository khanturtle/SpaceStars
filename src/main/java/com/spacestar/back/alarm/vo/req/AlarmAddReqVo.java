package com.spacestar.back.alarm.vo.req;

import com.spacestar.back.alarm.enums.AlarmType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmAddReqVo {

	private String senderUuid;
	private String content;
	private AlarmType alarmType;
}
