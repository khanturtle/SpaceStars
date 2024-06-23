package com.spacestar.back.rate.service;

import com.spacestar.back.rate.dto.req.RateAddReqDto;

public interface RateService {

	// 팀원 평가
	void addRate(String fromMemberUuid, RateAddReqDto rateAddReqDto);
}
