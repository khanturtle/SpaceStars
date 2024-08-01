package com.spacestar.back.alarm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;
import com.spacestar.back.global.GlobalCreateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document(collection = "alarms")
public class Alarm extends GlobalCreateTime {

	@Id
	private String id;
	private String receiverUuid;
	@NotNull
	private String senderUuid;
	@NotNull
	private String content;
	@NotNull
	private AlarmType alarmType;
	@NotNull
	private CheckStatus checkStatus;

	@Builder
	public Alarm(String id, String receiverUuid, String senderUuid, String content, AlarmType alarmType,
		CheckStatus checkStatus) {
		this.id = id;
		this.receiverUuid = receiverUuid;
		this.senderUuid = senderUuid;
		this.content = content;
		this.alarmType = alarmType;
		this.checkStatus = CheckStatus.UNREAD;
	}
}
