package com.spacestar.back.auth.dto.req;

import com.spacestar.back.auth.domain.Member;
import com.spacestar.back.auth.enums.GenderType;
import com.spacestar.back.auth.enums.UnregisterType;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinReqDto {

    private String email;
    private String nickname;
    private String imageUrl;
    private LocalDate birth;
    private GenderType gender;
    private boolean infoAgree;


    public static Member toEntity(String uuid, MemberJoinReqDto memberJoinReqDto) {

        return Member.builder()
                .uuid(uuid)
                .email(memberJoinReqDto.getEmail())
                .nickname(memberJoinReqDto.getNickname())
                .birth(memberJoinReqDto.getBirth())
                .gender(memberJoinReqDto.getGender())
                .unregister(UnregisterType.MEMBER)
                .infoAgree(memberJoinReqDto.isInfoAgree())
                .build();
    }

    public static Member updateEntity(String uuid, Long id, String email,MemberJoinReqDto memberJoinReqDto) {

        return Member.builder()
                .id(id)
                .uuid(uuid)
                .email(email)
                .nickname(memberJoinReqDto.getNickname())
                .birth(memberJoinReqDto.getBirth())
                .gender(memberJoinReqDto.getGender())
                .unregister(UnregisterType.MEMBER)
                .infoAgree(memberJoinReqDto.isInfoAgree())
                .build();
    }

}
