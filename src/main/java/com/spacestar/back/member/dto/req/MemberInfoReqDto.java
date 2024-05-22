package com.spacestar.back.member.dto.req;

import com.spacestar.back.member.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoReqDto {

    private String nickname;
    private String description;
    private GenderType gender;
    private LocalDate birth;
    private Long gamePreferenceId;
    private List<Long> likedGameIds;
    private List<MemberInfoGameReqDto> playGames;
    private Long mainGameId;
    private Long mbtiId;
    private boolean swipe;
}
