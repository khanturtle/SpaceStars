package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface QuickMatchingService {

    void connectSocket(WebSocketSession session);
    void enterQuickMatching(String uuid, String gameName);
}
