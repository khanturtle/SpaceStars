package com.spacestar.back.alarm.vo.res;

import java.time.LocalDateTime;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.DateType;
import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.Getter;

@Getter
public class AlarmResVo {

	private int index;
	private String senderUuid;
	private CheckStatus checkStatus;
}
