package com.spacestar.back.rate.domain;


import com.spacestar.back.global.GlobalTime;

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
@Builder
@NoArgsConstructor
public class Rate extends GlobalTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(length = 50)
	private String fromMemberUuid;

	@NotNull
	@Column(length = 50)
	private String toMemberUuid;

	@NotNull
	@Column(length = 30)
	private String roomNumber;

	@Column(length = 30)
	private String comment;

	@Column(length = 10)
	private short score;
	
	private Boolean isRate;

	public Rate(Long id, String fromMemberUuid, String toMemberUuid, String roomNumber, String comment, short score,
			Boolean isRate) {
		this.id = id;
		this.fromMemberUuid = fromMemberUuid;
		this.toMemberUuid = toMemberUuid;
		this.roomNumber = roomNumber;
		this.comment = comment;
		this.score = score;
		this.isRate = isRate;
	}
}
