package com.spacestar.back.member.dto.res;

import com.spacestar.back.member.domain.Member;
import com.spacestar.back.member.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileMatchingResDto {

    private String nickname;
    private String description;
    private GenderType gender;
    private LocalDate birth;
    private List<Long> likedGameIds;
    private List<Long> playGameIds;
    private Long mainGameId;
    private Long gamePreferenceId;
    private Long mbtiId;
    private Long exp;

    //프로필 상세
    private String email;
    private int reportCount;
    private boolean swipe;
    private LocalDateTime profileUpdateDate;

    public static ProfileMatchingResDto toDto(Member member, List<Long> likedGameIds, List<Long> playGameIds, Long mainGameid){

        return ProfileMatchingResDto.builder()
                .nickname(member.getNickname())
                .description(member.getDescription())
                .gender(member.getGender())
                .birth(member.getBirth())
                .likedGameIds(likedGameIds)
                .playGameIds(playGameIds)
                .mainGameId(mainGameid)
                .gamePreferenceId(member.getGamePreferenceId())
                .mbtiId(member.getMbtiId())
                .exp(member.getExp())

                .email(member.getEmail())
                .reportCount(member.getReportCount())
                .swipe(member.isSwipe())
                .profileUpdateDate(member.getUpdatedAt())
                .build();

    }
}
