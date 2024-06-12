package com.spacestar.back.alarm.domain;

import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;
import com.spacestar.back.global.GlobalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Alarm extends GlobalTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 30)
	private String receiverUuid;

	@NotNull
	@Column(length = 30)
	private String senderUuid;

	@NotNull
	@Column(length = 30)
	private String content;

	@NotNull
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private AlarmType alarmType;

	@NotNull
	@Column(length = 10)
	@Enumerated(EnumType.STRING)
	private CheckStatus checkStatus;

	@Builder
	public Alarm(Long id, String receiverUuid, String senderUuid, String content, AlarmType alarmType,
		CheckStatus checkStatus) {
		this.id = id;
		this.receiverUuid = receiverUuid;
		this.senderUuid = senderUuid;
		this.content = content;
		this.alarmType = alarmType;
		this.checkStatus = CheckStatus.UNREAD;
	}
}
