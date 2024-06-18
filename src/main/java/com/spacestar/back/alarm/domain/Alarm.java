package com.spacestar.back.alarm.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;
import com.spacestar.back.global.GlobalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Document(collection = "alarms")
public class Alarm extends GlobalTime {

	@Id
	private String id;
	@NotNull
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
