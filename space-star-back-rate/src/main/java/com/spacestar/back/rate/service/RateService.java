package com.spacestar.back.rate.service;

import com.spacestar.back.rate.dto.req.RateAddReqDto;
import com.spacestar.back.rate.dto.req.RateSkipReqDto;

public interface RateService {

	// 팀원 평가
	void addRate(String fromMemberUuid, RateAddReqDto rateAddReqDto);

	// 팀원 평가 건너뛰기
	void skipRate(String fromMemberUuid, RateSkipReqDto rateSkipReqDto);
}
