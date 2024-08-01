package com.spacestar.back.feignClient.controller;

import com.spacestar.back.feignClient.dto.req.OpenAiReqDto;
import com.spacestar.back.feignClient.dto.res.OpenAiResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "openAiClient", url = "${openai.url}")
public interface OpenAiClient {
    @PostMapping
    OpenAiResDto getChatCompletion(@RequestBody OpenAiReqDto request,
                                   @RequestHeader String openApiKey);
}