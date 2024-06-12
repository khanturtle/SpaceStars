package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface QuickMatchingService {

    void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto);

    SseEmitter connect(String gameName);
}
