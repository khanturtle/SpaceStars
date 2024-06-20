package com.spacestar.back.feignClient.service;

import com.spacestar.back.feignClient.controller.AuthClient;
import com.spacestar.back.feignClient.controller.ProfileClient;
import com.spacestar.back.feignClient.dto.res.AuthResDto;
import com.spacestar.back.feignClient.dto.res.ProfileResDto;
import com.spacestar.back.global.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeignClientServiceImpl implements FeignClientService{
    private final ProfileClient profileClient;
    private final AuthClient authClient;
    public ProfileResDto getProfile(String memberUuid) {
        org.springframework.http.ResponseEntity<ResponseEntity<ProfileResDto>> response = profileClient.getProfile(memberUuid);
        ResponseEntity<ProfileResDto> body = response.getBody();
        if (body == null || body.result() == null) {
            System.out.println("Profile data is missing or null for memberUuid: " + memberUuid);
            return new ProfileResDto();  // 기본값 반환
        }
        return body.result();
    }

    //FeignClient로 Auth 서비스 호출
    public AuthResDto getAuth(String memberUuid) {
        org.springframework.http.ResponseEntity<ResponseEntity<AuthResDto>> response = authClient.getAuth(memberUuid);
        ResponseEntity<AuthResDto> body = response.getBody();
        if (body == null || body.result() == null) {
            System.out.println("Auth data is missing or null for memberUuid: " + memberUuid);
            return new AuthResDto();  // 기본값 반환
        }
        return body.result();
    }
}
