package com.spacestar.back.member.dto.res;

import com.spacestar.back.member.domain.Member;
import com.spacestar.back.member.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileChattingResDto {

    private String mainProfileImageUrl;
    private String nickname;
    private Long mainGameId;
    private String description;

    public static ProfileChattingResDto toDto(Member member, String mainProfileUrl, Long mainGameId) {

        return ProfileChattingResDto.builder()
                .mainProfileImageUrl(mainProfileUrl)
                .nickname(member.getNickname())
                .mainGameId(mainGameId)
                .description(member.getDescription())
                .build();
    }
}
