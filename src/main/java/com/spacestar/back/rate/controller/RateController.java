package com.spacestar.back.rate.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.rate.dto.req.RateAddReqDto;
import com.spacestar.back.rate.service.RateService;
import com.spacestar.back.rate.vo.req.RateAddReqVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Rate", description = "평가")
@RequestMapping("/api/v1/rate")
public class RateController {

	private final RateService rateService;
	private final ModelMapper modelMapper;
	@PostMapping
	@Operation(summary = "팀원 평가")
	public ResponseEntity<Void> addRate(@RequestHeader("UUID") String uuid,
			@RequestBody RateAddReqVo rateAddReqVo){
		rateService.addRate(uuid, modelMapper.map(rateAddReqVo, RateAddReqDto.class));
		return new ResponseEntity<>(ResponseSuccess.RATE_CREATE_SUCCESS);
	}
}
