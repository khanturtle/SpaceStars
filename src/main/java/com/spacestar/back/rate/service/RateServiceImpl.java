package com.spacestar.back.rate.service;

import org.springframework.stereotype.Service;

import com.spacestar.back.rate.dto.req.RateAddReqDto;
import com.spacestar.back.rate.repository.RateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService{

	private final RateRepository rateRepository;
	
	// 팀원 평가
	@Override
	public void addRate(String fromMemberUuid, RateAddReqDto rateAddReqDto){
		rateRepository.save(RateAddReqDto.toEntity(fromMemberUuid, rateAddReqDto));
	}
}
