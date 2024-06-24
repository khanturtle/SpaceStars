package com.spacestar.back.auth.service;

import com.spacestar.back.auth.domain.Member;
import com.spacestar.back.auth.dto.req.MemberJoinReqDto;
import com.spacestar.back.auth.dto.req.MemberLoginReqDto;
import com.spacestar.back.auth.dto.res.MemberLoginResDto;
import com.spacestar.back.auth.dto.res.NicknameExistResDto;
import com.spacestar.back.auth.dto.res.NicknameResDto;
import com.spacestar.back.auth.dto.res.UuidResDto;
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
                .accessToken("Bearer " + jwtUtil.createJwt(member.getUuid(), "ROLE_USER", 30L * 24 * 60 * 60 * 1000))
                .uuid(member.getUuid())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birth(member.getBirth())
                .gender(member.getGender())
                .infoAgree(member.isInfoAgree())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .isProfile(member.isProfile())
                .build();

    }

    @Override
    public NicknameExistResDto checkNickname(String nickname) {

            Optional<Member> member = memberRepository.findByNickname(nickname);

            if (member.isPresent()) {
                return NicknameExistResDto.builder()
                        .isExist(true)
                        .message("이미 존재하는 닉네임입니다.")
                        .build();
            } else {
                return NicknameExistResDto.builder()
                        .isExist(false)
                        .message("사용 가능한 닉네임입니다.")
                        .build();
            }
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
}
