package com.spacestar.back.profile.vo.req;

import com.spacestar.back.profile.dto.req.LikedGameInfoReqDto;
import lombok.Getter;

import java.util.List;

@Getter
public class LikedGameInfoReqVo {

    private List<Long> likedGameIdList;
}
