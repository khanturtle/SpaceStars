package com.spacestar.back.member.domain;

import com.spacestar.back.global.GlobalTime;
import com.spacestar.back.member.dto.req.MemberInfoReqDto;
import com.spacestar.back.member.dto.req.MemberJoinReqDto;
import com.spacestar.back.member.enums.GenderType;
import com.spacestar.back.member.enums.UnregisterType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends GlobalTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String email;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    private LocalDate birth;

    private boolean infoAgree;

    @Enumerated(EnumType.STRING)
    private UnregisterType unregister;

    private int reportCount;

    private String description;

    private Long gamePreferenceId;

    private Long mbtiId;

    private boolean swipe;

    private Long exp;

    @Builder
    public Member(Long id, String uuid, String email, String nickname, GenderType gender, LocalDate birth, boolean infoAgree, UnregisterType unregister, int reportCount, String description, Long gamePreferenceId, Long mbtiId, boolean swipe, Long exp) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
        this.infoAgree = infoAgree;
        this.unregister = unregister;
        this.reportCount = reportCount;
        this.description = description;
        this.gamePreferenceId = gamePreferenceId;
        this.mbtiId = mbtiId;
        this.swipe = swipe;
        this.exp = exp;
    }


    public static Member toEntity(MemberJoinReqDto memberJoinReqDto) {

        String uuid = UUID.randomUUID().toString();

        return Member.builder()
                .uuid(uuid)
                .email(memberJoinReqDto.getEmail())
                .nickname(memberJoinReqDto.getNickname())
                .gender(memberJoinReqDto.getGender())
                .birth(memberJoinReqDto.getBirth())
                .infoAgree(memberJoinReqDto.isInfoAgree())
                .unregister(UnregisterType.MEMBER)
                .reportCount(0)
                .description(null)
                .gamePreferenceId(null)
                .mbtiId(null)
                .swipe(true)
                .exp(0L)
                .build();
    }

    public static Member updateToEntity(Member member, MemberInfoReqDto memberInfoReqDto){
        return Member.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .email(member.getEmail())
                .nickname(memberInfoReqDto.getNickname())
                .gender(memberInfoReqDto.getGender())
                .birth(memberInfoReqDto.getBirth())
                .infoAgree(member.isInfoAgree())
                .unregister(member.getUnregister())
                .reportCount(member.getReportCount())
                .description(memberInfoReqDto.getDescription())
                .gamePreferenceId(memberInfoReqDto.getGamePreferenceId())
                .mbtiId(memberInfoReqDto.getMbtiId())
                .swipe(memberInfoReqDto.isSwipe())
                .exp(member.getExp())
                .build();
    }
}
