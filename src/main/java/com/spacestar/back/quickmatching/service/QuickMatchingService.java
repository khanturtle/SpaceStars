package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import org.springframework.web.socket.WebSocketSession;

public interface QuickMatchingService {
    void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqVo);

    void connectSocket(WebSocketSession session);

    void waitForMatching(String id, String token);
}
