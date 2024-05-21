package com.spacestar.back.swipe.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.swipe.service.SwipeService;
import com.spacestar.back.swipe.vo.SwipeReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/swipe")
public class SwipeController {
    private final SwipeService swipeService;
    @PostMapping("/add")
    public ResponseEntity<?> addSwipe(
        @RequestBody SwipeReqVo swipeReqVo
    ){
        swipeService.addSwipe();
        return null;
    }
}
