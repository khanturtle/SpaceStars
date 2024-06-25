package com.spacestar.back.rate.service;

import org.springframework.stereotype.Service;

import com.spacestar.back.rate.domain.Experience;
import com.spacestar.back.rate.dto.req.RateAddReqDto;
import com.spacestar.back.rate.dto.req.RateSkipReqDto;
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

	// 팀원 평가 미루기
	@Override
	public void skipRate(String fromMemberUuid, RateSkipReqDto rateSkipReqDto){
		rateRepository.save(RateSkipReqDto.toEntity(fromMemberUuid, rateSkipReqDto));
	}

	private void addExperience(String uuid, RateAddReqDto rateAddReqDto){

	}
}
