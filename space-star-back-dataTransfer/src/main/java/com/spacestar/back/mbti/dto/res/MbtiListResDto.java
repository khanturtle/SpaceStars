package com.spacestar.back.mbti.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MbtiListResDto {

    private Long id;
    private String mbtiName;
}
