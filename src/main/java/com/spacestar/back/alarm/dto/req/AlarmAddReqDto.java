package com.spacestar.back.alarm.dto.req;

import com.spacestar.back.alarm.domain.Alarm;
import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;
import com.spacestar.back.kafka.message.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmAddReqDto {
	private String senderUuid;
	private String content;
	private AlarmType alarmType;

	public static Alarm toEntity(String uuid, AlarmAddReqDto alarmAddReqDto) {
		return Alarm.builder()
			.receiverUuid(uuid)
			.senderUuid(alarmAddReqDto.getSenderUuid())
			.content(alarmAddReqDto.getContent())
			.alarmType(alarmAddReqDto.getAlarmType())
			.checkStatus(CheckStatus.UNREAD)
			.build();
	}

	public static Alarm toEntitySSE(String uuid, Message message) {
		return Alarm.builder()
			.receiverUuid(message.getReceiverUuid())
			.senderUuid(message.getSenderUuid())
			.content(message.getContent())
			.alarmType(message.getMessageType())
			.checkStatus(CheckStatus.UNREAD)
			.build();
	}
}
