package com.spacestar.back.quickmatching.controller;

import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.quickmatching.dto.req.QuickMatchingEnterReqDto;
import com.spacestar.back.quickmatching.dto.res.QuickMatchingResDto;
import com.spacestar.back.quickmatching.service.QuickMatchingService;
import com.spacestar.back.quickmatching.vo.req.QuickMatchingEnterReqVo;
import com.spacestar.back.quickmatching.vo.res.QuickMatchingResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Queue", description = "빠른 매칭(큐)")
@RequestMapping("/api/v1/quick-matching")
@RequiredArgsConstructor
public class QuickMatchingController {
    private final QuickMatchingService quickMatchingService;
    private final ModelMapper mapper;

    @Operation(summary = "대기 큐 진입")
    @PostMapping
    public ResponseEntity<Void> enterQuickMatching(@RequestHeader("UUID") String uuid,
                                                   @RequestBody QuickMatchingEnterReqVo reqVo) {
        quickMatchingService.enterQuickMatching(uuid, mapper.map(reqVo, QuickMatchingEnterReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_ENTER_SUCCESS);
    }

    @Operation(summary = "대기 큐 취소")
    @DeleteMapping
    public ResponseEntity<Void> quitQuickMatching(@RequestHeader("UUID") String uuid,
                                                  @RequestBody QuickMatchingEnterReqVo reqVo) {
        quickMatchingService.quitQuickMatching(uuid, mapper.map(reqVo, QuickMatchingEnterReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_QUIT_SUCCESS);
    }

    @Operation(summary = "큐 요청 수락하기")
    @PatchMapping("/accept")
    public ResponseEntity<Void> acceptQuickMatch(@RequestHeader("UUID") String uuid) {
        quickMatchingService.acceptQuickMatch(uuid);
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_ACCEPT_SUCCESS);
    }

    @Operation(summary = "큐 요청 거절하기")
    @PatchMapping("/reject")
    public ResponseEntity<Void> rejectQuickMatch(@RequestHeader("UUID") String uuid) {
        quickMatchingService.rejectQuickMatch(uuid);
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_REJECT_SUCCESS);
    }

    @Operation(summary = "수락 큐 결과")
    @PostMapping("/complete")
    public ResponseEntity<QuickMatchingResVo> completeQuickMatch(@RequestHeader("UUID") String uuid,
                                                                 @RequestBody QuickMatchingEnterReqVo reqVo) {
        QuickMatchingResDto matchedMember = quickMatchingService.completeQuickMatch(uuid, mapper.map(reqVo, QuickMatchingEnterReqDto.class));

        if (matchedMember == null) {
            return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_COMPLETE_FAIL);
        }
        return new ResponseEntity<>(ResponseSuccess.QUICK_MATCHING_COMPLETE_SUCCESS,
                mapper.map(matchedMember, QuickMatchingResVo.class));
    }

}
