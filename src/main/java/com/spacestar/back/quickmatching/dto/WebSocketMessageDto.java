package com.spacestar.back.quickmatching.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.*;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"type", "data"})
public class WebSocketMessageDto {
    String type;  // (전송) expectTime, findPair, matchingSuccess, matchingFail / (수신) accept
    Map<String, String> data;

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseStatus.TOKEN_NOT_VALID);
        }
    }
}
