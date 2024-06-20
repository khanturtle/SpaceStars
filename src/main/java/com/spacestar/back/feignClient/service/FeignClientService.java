package com.spacestar.back.feignClient.service;

import com.spacestar.back.feignClient.dto.res.AuthResDto;
import com.spacestar.back.feignClient.dto.res.ProfileResDto;
import org.springframework.stereotype.Service;

@Service
public interface FeignClientService{
    ProfileResDto getProfile(String memberUuid);
    AuthResDto getAuth(String memberUuid);
}
