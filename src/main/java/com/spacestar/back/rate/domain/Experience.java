package com.spacestar.back.rate.domain;

import com.spacestar.back.global.GlobalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Experience extends GlobalTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String uuid;

	@Column(length = 10)
	private Long changeExp;

	public Experience(Long id, String uuid, Long changeExp) {
		this.id = id;
		this.uuid = uuid;
		this.changeExp = changeExp;
	}
}
