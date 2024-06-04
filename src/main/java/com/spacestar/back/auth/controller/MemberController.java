package com.spacestar.back.auth.controller;

import com.spacestar.back.auth.service.MemberService;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member", description = "member")
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 탈퇴(자발적)")
    @PatchMapping("/withdrawal")
    public ResponseEntity<Void> withdrawal(@RequestHeader("UUID") String uuid){

        memberService.withdrawal(uuid);
        return new ResponseEntity<>(ResponseSuccess.WITHDRAWAL_SUCCESS);
    }

    @Operation(summary = "회원 탈퇴(영구 탈퇴)" )
    @PatchMapping("/withdrawal/force")
    public ResponseEntity<Void> withdrawalForce(@RequestHeader("UUID") String uuid){

        memberService.withdrawalForce(uuid);
        return new ResponseEntity<>(ResponseSuccess.WITHDRAWAL_FORCE_SUCCESS);
    }

}
