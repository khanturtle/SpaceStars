package com.spacestar.back.feignClient.dto.res;

import com.spacestar.back.feignClient.dto.req.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiResDto {
    private List<Choice> choices;
    @Getter
    public static class Choice {
        private Message message;
        public void setMessage(Message message) {
            this.message = message;
        }
    }
}