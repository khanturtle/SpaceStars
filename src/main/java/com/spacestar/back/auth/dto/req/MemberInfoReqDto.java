package com.spacestar.back.auth.dto.req;

import com.spacestar.back.auth.domain.Member;
import com.spacestar.back.auth.enums.GenderType;
import com.spacestar.back.auth.enums.UnregisterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberInfoReqDto {

    private String nickname;
    private GenderType gender;
    private LocalDate birth;
    private boolean infoAgree;

    public static Member updateToEntity(Long id, String uuid, String email, MemberInfoReqDto memberInfoReqDto) {

        return Member.builder()
                .id(id)
                .uuid(uuid)
                .email(email)
                .nickname(memberInfoReqDto.getNickname())
                .gender(memberInfoReqDto.getGender())
                .birth(memberInfoReqDto.getBirth())
                .unregister(UnregisterType.MEMBER)
                .infoAgree(memberInfoReqDto.isInfoAgree())
                .build();
    }
}
