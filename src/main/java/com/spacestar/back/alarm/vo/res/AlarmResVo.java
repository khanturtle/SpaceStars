package com.spacestar.back.alarm.vo.res;

import java.time.LocalDateTime;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.DateType;
import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmResVo {

	private int index;
	private String senderUuid;
	private CheckStatus checkStatus;
	private AlarmType alarmType;
}
