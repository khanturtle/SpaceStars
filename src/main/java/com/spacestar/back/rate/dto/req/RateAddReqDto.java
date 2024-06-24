package com.spacestar.back.rate.dto.req;

import com.spacestar.back.rate.domain.Rate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateAddReqDto {

	private String toMemberUuid;
	private String comment;
	private short score;
	private String roomNumber;

	public static Rate toEntity(String fromMemberUuid, RateAddReqDto rateAddReqDto){
		return Rate.builder()
				.fromMemberUuid(fromMemberUuid)
				.toMemberUuid(rateAddReqDto.getToMemberUuid())
				.comment(rateAddReqDto.getComment())
				.score(rateAddReqDto.getScore())
				.isRate(true)
				.roomNumber(rateAddReqDto.getRoomNumber())
				.build();
	}
}
