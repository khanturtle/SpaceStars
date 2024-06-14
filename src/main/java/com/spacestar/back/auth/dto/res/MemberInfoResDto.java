package com.spacestar.back.auth.dto.res;

import com.spacestar.back.auth.enums.GenderType;
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
public class MemberInfoResDto {

    private String email;
    private String nickname;
    private LocalDate birth;
    private GenderType gender;
    private boolean infoAgree;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
