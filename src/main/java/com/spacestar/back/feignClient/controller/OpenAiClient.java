package com.spacestar.back.feignClient.controller;

import com.spacestar.back.config.OpenAiConfig;
import com.spacestar.back.feignClient.dto.req.OpenAiReqDto;
import com.spacestar.back.feignClient.dto.res.OpenAiResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "openAiClient", url = "${openai.url}",configuration = OpenAiConfig.class)
public interface OpenAiClient {
    @PostMapping
    OpenAiResDto getChatCompletion(
            @RequestBody OpenAiReqDto request);
}