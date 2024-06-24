package com.spacestar.back.rate.vo.req;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RateAddReqVo {

	private String toMemberUuid;
	private String comment;
	private short score;
	private String roomNumber;
}
