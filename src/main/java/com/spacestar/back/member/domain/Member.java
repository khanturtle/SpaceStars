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

    @Column
    private String uuid;

    @Column
    private String email;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column
    private GenderType gender;

    @Column
    private LocalDate birth;

    @Column
    private Boolean infoAgree;

    @Enumerated(EnumType.STRING)
    @Column
    private UnregisterType unregister;

    @Column
    private Integer reportCount;

    @Column
    private String description;

    @Column
    private Long gamePreferenceId;

    @Column
    private Long mbtiId;

    @Column
    private boolean swipe;

    @Column
    private Long exp;

    @Builder
    public Member(Long id, String uuid, String email, String nickname, GenderType gender, LocalDate birth, Boolean infoAgree, UnregisterType unregister, Integer reportCount, String description, Long gamePreferenceId, Long mbtiId, boolean swipe, Long exp) {
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
                .infoAgree(memberJoinReqDto.getInfoAgree())
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
                .uuid(member.getUuid())
                .email(member.getEmail())
                .nickname(memberInfoReqDto.getNickname())
                .gender(memberInfoReqDto.getGender())
                .birth(memberInfoReqDto.getBirth())
                .infoAgree(member.getInfoAgree())
                .unregister(member.getUnregister())
                .reportCount(member.getReportCount())
                .description(member.getDescription())
                .gamePreferenceId(memberInfoReqDto.getGamePreferenceId())
                .mbtiId(memberInfoReqDto.getMbtiId())
                .swipe(memberInfoReqDto.isSwipe())
                .exp(member.getExp())
                .build();
    }
}
