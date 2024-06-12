package com.spacestar.back.profile.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikedGameInfoReqDto {

    private List<Long> likedGameIdList;
}
