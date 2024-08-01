package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.dto.res.SwipeMemberInfoResDto;

import java.util.List;

public interface CustomProfileRepository {
    List<SwipeMemberInfoResDto> findWaitRequest();
}
