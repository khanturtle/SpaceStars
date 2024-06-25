package com.spacestar.back.feignClient.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class OpenAiReqDto {
    private String model;
    private List<Message> messages;

    public OpenAiReqDto(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

}