package com.spacestar.back.swipe.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.swipe.dto.SwipeReqDto;
import com.spacestar.back.swipe.service.SwipeService;
import com.spacestar.back.swipe.vo.SwipeReqVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/swipe")
public class SwipeController {
    private final SwipeService swipeService;
    private final ModelMapper mapper;
    @PostMapping("/add")
    public ResponseEntity<Void> addSwipe(
            @RequestBody SwipeReqVo swipeReqVo,
            @RequestHeader("Authorization") String uuid
            ){
        swipeService.addSwipe(mapper.map(swipeReqVo, SwipeReqDto.class),uuid);
        return new ResponseEntity<>(ResponseSuccess.SWIPE_ADD_SUCCESS);
    }
}
