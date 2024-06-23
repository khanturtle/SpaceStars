package com.spacestar.back.rate.vo.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RateSkipReqVo {

	private String toMemberUuid;
	private String roomNumber;
}
