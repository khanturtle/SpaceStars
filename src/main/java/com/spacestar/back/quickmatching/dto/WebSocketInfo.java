package com.spacestar.back.quickmatching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Setter
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