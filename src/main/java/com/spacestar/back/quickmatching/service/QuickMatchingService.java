package com.spacestar.back.quickmatching.service;

import com.spacestar.back.quickmatching.dto.QuickMatchingEnterReqDto;

public interface QuickMatchingService {

    void enterQuickMatching(String uuid, QuickMatchingEnterReqDto reqDto);
}
