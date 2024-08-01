package com.spacestar.back.feignClient.controller;

import com.spacestar.back.feignClient.dto.res.AuthResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "authClient", url = "${spring.application.auth-url}")
public interface AuthClient {
    @GetMapping("/{uuid}")
    ResponseEntity<com.spacestar.back.global.ResponseEntity<AuthResDto>> getAuth(@PathVariable("uuid") String uuid);
}
