package com.spacestar.back.auth.service;

import com.spacestar.back.auth.domain.Member;
import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.dto.res.NicknameResDto;
import com.spacestar.back.auth.enums.UnregisterType;
import com.spacestar.back.auth.jwt.JWTUtil;
import com.spacestar.back.auth.repository.MemberRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.spacestar.back.auth.enums.UnregisterType.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthServiceImp implements AuthService {

    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void addMember(MemberJoinReqDto memberJoinReqDto) {

        Optional<Member> memberInfo = memberRepository.findByEmail(memberJoinReqDto.getEmail());
        if (memberInfo.isPresent()) {


            //현재 가입한 회원
            if (memberInfo.get().getUnregister() == MEMBER) {
                throw new GlobalException(ResponseStatus.DUPLICATED_MEMBERS);
            }
            //영구 탈퇴 회원
            if (memberInfo.get().getUnregister() == BLACKLIST) {
                throw new GlobalException(ResponseStatus.BLACKLIST_MEMBER);
            }
            //자발적 탈퇴 회원
            if (memberInfo.get().getUnregister() == DELETED) {

                memberRepository.save(MemberJoinReqDto.updateEntity(memberInfo.get().getUuid(), memberInfo.get().getId(), memberInfo.get().getEmail(), memberJoinReqDto));

            }
        } else {
            String uuid = UUID.randomUUID().toString();
            memberRepository.save(MemberJoinReqDto.toEntity(uuid, memberJoinReqDto));
        }
    }

    @Override
    public MemberLoginResDto kakaoLogin(MemberLoginReqDto memberLoginReqDto) {

        Member member = memberRepository.findByEmail(memberLoginReqDto.getEmail())
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        //영구 탈퇴 회원
        if (member.getUnregister() == BLACKLIST) {
            throw new GlobalException(ResponseStatus.BLACKLIST_MEMBER);
        }
        //탈퇴 회원
        if (member.getUnregister() == DELETED) {
            throw new GlobalException(ResponseStatus.DELETE_MEMBER);
        }

        return MemberLoginResDto.builder()
                .accessToken("Bearer " + jwtUtil.createJwt(member.getUuid(), "ROLE_USER", 3600000L))
                .uuid(member.getUuid())
                .build();

    }

    @Override
    public void withdrawal(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        memberRepository.save( Member.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .email(member.getEmail())
                .birth(null)
                .gender(null)
                .unregister(UnregisterType.DELETED)
                .infoAgree(false)
                .build());
    }

    @Override
    public void withdrawalForce(String uuid) {

        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_MEMBER));

        memberRepository.save(Member.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .email(member.getEmail())
                .birth(null)
                .gender(null)
                .unregister(UnregisterType.BLACKLIST)
                .infoAgree(false)
                .build());
    }

}
