package com.spacestar.back.quickmatching.feignClient;

import com.spacestar.back.quickmatching.dto.res.ProfileResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profileClient", url = "${spring.application.profile-url}")
public interface ProfileClient {

    @GetMapping("/{uuid}")
    ResponseEntity<com.spacestar.back.global.ResponseEntity<ProfileResDto>> getProfile(@PathVariable("uuid") String uuid);
}