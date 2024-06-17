package com.spacestar.back.auth.service;

import com.spacestar.back.auth.domain.Member;
import com.spacestar.back.auth.dto.req.MemberInfoReqDto;
import com.spacestar.back.auth.dto.res.MemberInfoResDto;
import com.spacestar.back.auth.dto.res.NicknameResDto;
import com.spacestar.back.auth.dto.res.QuickAuthInfoResDto;
import com.spacestar.back.auth.dto.res.UuidResDto;
import com.spacestar.back.auth.enums.UnregisterType;
import com.spacestar.back.auth.repository.MemberRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void withdrawal(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        memberRepository.save(Member.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .email(member.getEmail())
                .nickname(null)
                .birth(null)
                .gender(null)
                .unregister(UnregisterType.DELETED)
                .infoAgree(false)
                .build());
    }

    @Transactional
    @Override
    public void withdrawalForce(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        memberRepository.save(Member.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .email(member.getEmail())
                .nickname(null)
                .birth(null)
                .gender(null)
                .unregister(UnregisterType.BLACKLIST)
                .infoAgree(false)
                .build());
    }


    @Transactional
    @Override
    public void updateMemberInfo(String uuid, MemberInfoReqDto memberInfoReqDto) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));
        memberRepository.save(MemberInfoReqDto.updateToEntity(member.getId(), uuid, member.getEmail(), memberInfoReqDto));
    }

    @Transactional(readOnly = true)
    @Override
    public NicknameResDto getNickname(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        return NicknameResDto.builder()
                .nickname(member.getNickname())
                .build();
    }

    @Override
    public UuidResDto getUuid(String nickname) {

        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        return UuidResDto.builder()
                .uuid(member.getUuid())
                .build();
    }

    @Override
    public MemberInfoResDto findMemberInfo(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        return MemberInfoResDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birth(member.getBirth())
                .gender(member.getGender())
                .infoAgree(member.isInfoAgree())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    @Override
    public QuickAuthInfoResDto findQuickAuthInfo(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        //나이 계산
        LocalDate today = LocalDate.now();
        int age = today.getYear() - member.getBirth().getYear();

        return QuickAuthInfoResDto.converter(age, member.getGender());
    }
}
