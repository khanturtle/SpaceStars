package com.spacestar.back.swipe;

import com.spacestar.back.swipe.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SwipeScheduler {
    private final SwipeService swipeService;
    @Scheduled(cron = "0 0 0/1 * * *")  // 1시간 마다 실행
    public void deleteExpiredSwipe(){
        swipeService.deleteExpiredSwipe();
    }
}
