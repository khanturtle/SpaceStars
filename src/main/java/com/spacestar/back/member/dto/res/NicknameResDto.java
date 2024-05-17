package com.spacestar.back.member.dto.res;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NicknameResDto {

    private boolean duplicated;
    private String message;
}
