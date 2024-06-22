package com.spacestar.back.rate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class Rate {

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
}
