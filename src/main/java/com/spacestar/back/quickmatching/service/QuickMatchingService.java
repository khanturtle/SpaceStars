package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.QuickMatchingResDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface QuickMatchingService {

    void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto);

    SseEmitter connect(QuickMatchingEnterReqDto reqDto);

    void acceptQuickMatch(String uuid);

    void rejectQuickMatch(String uuid);

    QuickMatchingResDto completeQuickMatch(String uuid,QuickMatchingEnterReqDto reqDto);

    void quitQuickMatching(String uuid,QuickMatchingEnterReqDto reqDto);
}
