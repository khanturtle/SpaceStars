package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.req.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.res.QuickMatchingResDto;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface QuickMatchingService {

    void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto);

    SseEmitter connect(String gameName, String uuid);

    void acceptQuickMatch(String uuid);

    void rejectQuickMatch(String uuid);

    QuickMatchingResDto completeQuickMatch(String uuid,QuickMatchingEnterReqDto reqDto);

    void quitQuickMatching(String uuid,QuickMatchingEnterReqDto reqDto);

    String getStrings();
}
