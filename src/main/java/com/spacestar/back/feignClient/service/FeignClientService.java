package com.spacestar.back.feignClient.service;

import com.spacestar.back.feignClient.dto.res.AuthResDto;
import com.spacestar.back.feignClient.dto.res.ProfileResDto;
import com.spacestar.back.feignClient.vo.res.SwipeMemberInfoResVo;

import java.util.List;

public interface FeignClientService {
    ProfileResDto getProfile(String memberUuid);

    AuthResDto getAuth(String memberUuid);

    List<String> getOpenAi(String uuid);

    List<SwipeMemberInfoResVo> getProfileList();
}
