package com.spacestar.back.alarm.dto.res;

import java.time.LocalDateTime;

import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
public class AlarmResDto {
	private int index;
	private String senderUuid;
	private CheckStatus checkStatus;
	private AlarmType alarmType;
	private String content;
	private String alarmId;
}
