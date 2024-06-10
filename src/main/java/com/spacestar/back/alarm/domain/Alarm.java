package com.spacestar.back.alarm.domain;

import com.spacestar.back.alarm.enums.AlarmType;
import com.spacestar.back.alarm.enums.CheckStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Alarm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(length = 30)
	private String reciverUuid;

	@NotNull
	@Column(length = 30)
	private String senderUuid;

	@NotNull
	@Column(length = 30)
	private String content;

	@NotNull
	@Column(length = 10)
	private AlarmType alarmType;

	@NotNull
	@Column(length = 10)
	private CheckStatus checkStatus;

	@Builder
	public Alarm(Long id, String reciverUuid, String senderUuid, String content, AlarmType alarmType,
		CheckStatus checkStatus) {
		this.id = id;
		this.reciverUuid = reciverUuid;
		this.senderUuid = senderUuid;
		this.content = content;
		this.alarmType = alarmType;
		this.checkStatus = CheckStatus.UNREAD;
	}
}
