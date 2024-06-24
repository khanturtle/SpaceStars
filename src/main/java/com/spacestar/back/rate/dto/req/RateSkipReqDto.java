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
public class RateSkipReqDto {

	private String toMemberUuid;
	private String roomNumber;

	public static Rate toEntity(String fromMemberUuid, RateSkipReqDto rateSkipReqDto){

		return Rate.builder()
				.fromMemberUuid(fromMemberUuid)
				.toMemberUuid(rateSkipReqDto.getToMemberUuid())
				.comment(" ")
				.score((short)0)
				.isRate(false)
				.roomNumber(rateSkipReqDto.getRoomNumber())
				.build();
	}
}
