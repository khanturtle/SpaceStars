package com.spacestar.back.alarm.vo.req;

import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmModifyReqVo {

	private String alarmId;
	private CheckStatus checkStatus;
}
