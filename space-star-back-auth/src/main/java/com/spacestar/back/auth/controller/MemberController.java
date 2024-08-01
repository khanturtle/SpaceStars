package com.spacestar.back.auth.controller;

import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.res.MemberInfoResDto;
import com.spacestar.back.auth.service.MemberService;
import com.spacestar.back.auth.vo.req.MemberInfoReqVo;
import com.spacestar.back.auth.vo.res.*;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Member", description = "member")
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper mapper;

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


    @Operation(summary = "회원 정보 수정")
    @PatchMapping("/info")
    public ResponseEntity<Void> updateMemberInfo(@RequestHeader("UUID") String uuid,
                                                 @RequestBody @Valid MemberInfoReqVo memberInfoReqVo){

        memberService.updateMemberInfo(uuid, mapper.map(memberInfoReqVo, MemberInfoReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.MEMBER_INFO_UPDATE_SUCCESS);
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping("/info")
    public ResponseEntity<MemberInfoResVo> findMemberInfo(@RequestHeader("UUID") String uuid){

        return new ResponseEntity<>(ResponseSuccess.MEMBER_INFO_SELECT_SUCCESS,
                mapper.map(memberService.findMemberInfo(uuid), MemberInfoResVo.class));
    }

    @Operation(summary = "다른 회원 정보 조회")
    @GetMapping("/info/{uuid}")
    public ResponseEntity<MemberInfoResVo> findYourMemberInfo(@PathVariable("uuid") String targetUuid){

        return new ResponseEntity<>(ResponseSuccess.MEMBER_INFO_SELECT_SUCCESS,
                mapper.map(memberService.findMemberInfo(targetUuid), MemberInfoResVo.class));
    }

    @Tag(name = "select", description = "조회용")
    @Operation(summary = "빠른 매칭용 사용자 정보 조회")
    @GetMapping("/quick-matching/{uuid}")
    public ResponseEntity<QuickAuthInfoResVo> findQuickAuthInfo(@PathVariable("uuid") String uuid){

        return new ResponseEntity<>(ResponseSuccess.QUICK_MEMBER_INFO_SELECT_SUCCESS,
                mapper.map(memberService.findQuickAuthInfo(uuid), QuickAuthInfoResVo.class));
    }


}
