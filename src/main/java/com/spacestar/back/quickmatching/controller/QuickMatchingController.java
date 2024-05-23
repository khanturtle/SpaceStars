package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.quickmatching.service.QuickMatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quick-matching")
@RequiredArgsConstructor
public class QuickMatchingController {
    private final QuickMatchingService quickMatchingService;
    @PostMapping
    public ResponseEntity<Void> enterQuickMatching(@RequestHeader("uuid")String uuid){
        quickMatchingService.enterQuickMatching(uuid);
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_ENTER_SUCCESS);
    }
}
