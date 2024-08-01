package com.spacestar.back.alarm.dto.req;

import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Getter;

@Getter
public class AlarmModifyReqDto {

	private String alarmId;
	private CheckStatus checkStatus;
}
