package com.spacestar.back.quickmatching.dto;

import lombok.*;
import org.springframework.web.socket.WebSocketSession;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketInfo {
    WebSocketSession session;
    String memberUuid;
    int count;

    public WebSocketInfo(WebSocketSession session, int count) {
        this.session = session;
        this.count = count;
    }
    public void countUp() {
        this.count += 10;
    }
}