package com.spacestar.back.rate.repository.level;

import com.spacestar.back.rate.dto.res.LevelResDto;

public interface LevelRepositoryCustom {
    LevelResDto getLevel(Long exp);
}
