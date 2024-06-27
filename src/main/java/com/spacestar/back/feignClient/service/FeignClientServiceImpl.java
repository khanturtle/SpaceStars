package com.spacestar.back.feignClient.service;

import com.spacestar.back.feignClient.controller.AuthClient;
import com.spacestar.back.feignClient.controller.OpenAiClient;
import com.spacestar.back.feignClient.controller.ProfileClient;
import com.spacestar.back.feignClient.dto.req.Message;
import com.spacestar.back.feignClient.dto.req.OpenAiReqDto;
import com.spacestar.back.feignClient.dto.res.AuthResDto;
import com.spacestar.back.feignClient.dto.res.OpenAiResDto;
import com.spacestar.back.feignClient.dto.res.ProfileResDto;
import com.spacestar.back.feignClient.dto.res.SwipeMemberInfoResDto;
import com.spacestar.back.feignClient.vo.res.SwipeMemberInfoResVo;
import com.spacestar.back.global.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeignClientServiceImpl implements FeignClientService {
    private final ProfileClient profileClient;
    private final AuthClient authClient;
    private final OpenAiClient openAiClient;
    @Value("${openai.key}")
    private String openAiKey;

    @Override
    public ProfileResDto getProfile(String memberUuid) {
        org.springframework.http.ResponseEntity<ResponseEntity<ProfileResDto>> response = profileClient.getProfile(memberUuid);
        ResponseEntity<ProfileResDto> body = response.getBody();
        if (body == null || body.result() == null) {
            System.out.println("Profile data is missing or null for memberUuid: " + memberUuid);
            return new ProfileResDto();  // 기본값 반환
        }
        return body.result();
    }

    @Override
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

    @Override
    public List<SwipeMemberInfoResVo> getProfileList() {
        org.springframework.http.ResponseEntity<ResponseEntity<List<SwipeMemberInfoResVo>>> response = profileClient.getSwipeProfile();
        ResponseEntity<List<SwipeMemberInfoResVo>> body = response.getBody();
        assert body != null;
        return body.result();
    }

    @Override
    public List<String> getOpenAi(String uuid) {
        List<SwipeMemberInfoResVo> profileList = getProfileList();
        Set<String> profileUuidSet = profileList.stream()
                .map(SwipeMemberInfoResVo::getUuid)
                .collect(Collectors.toSet());

        String profileListStr = profileList.stream()
                .map(SwipeMemberInfoResVo::toString)
                .collect(Collectors.joining(", "));
        String prompt = String.format("이 데이터들 중에 [%s] 이 데이터와 성향이 잘 맞는 순서대로 이 데이터를 제외한 모든 사용자의 uuid를 출력해줘.성향이 잘 맞는건 mainGameId가 일치하는지를 최우선으로 보면돼.반환 형식은 9135f6d9-b4d4-4397-9b63-ff3369d531c2,3e0b5a99-d5fd-407c-8fde-59940dc512e4,jkl012 이런식으로 해줘.", SwipeMemberInfoResDto.toDto(getProfile(uuid), uuid).toString());
        Message message = new Message("user", profileListStr + prompt);

        OpenAiReqDto request = new OpenAiReqDto("gpt-3.5-turbo", List.of(message));
        OpenAiResDto chatGPTResponse = openAiClient.getChatCompletion(request,openAiKey);
        String response = chatGPTResponse.getChoices().get(0).getMessage().getContent();

        String[] tokens = response.substring(1, response.length() - 1).split(",\\s*");
        List<String> swipeResDtoList = new ArrayList<>(Arrays.asList(tokens));

        swipeResDtoList = swipeResDtoList.stream()
                .filter(profileUuidSet::contains)
                .limit(20)
                .toList();
        return swipeResDtoList;
    }
}
