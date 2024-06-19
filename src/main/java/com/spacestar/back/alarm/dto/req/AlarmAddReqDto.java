package com.spacestar.back.alarm.dto.req;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmAddReqDto {
	private String senderUuid;
	private String content;
	private AlarmType alarmType;

	public static Alarm toEntity(String uuid, AlarmAddReqDto alarmAddReqDto){
		return  Alarm.builder()
				.receiverUuid(uuid)
				.senderUuid(alarmAddReqDto.getSenderUuid())
				.content(alarmAddReqDto.getContent())
				.alarmType(alarmAddReqDto.getAlarmType())
				.checkStatus(CheckStatus.UNREAD)
				.build();
	}
}
