package com.spacestar.back.rate.repository.level;

import com.spacestar.back.rate.domain.Level;
import com.spacestar.back.rate.dto.res.LevelInfoResDto;
import com.spacestar.back.rate.dto.res.LevelResDto;

public interface LevelRepositoryCustom {
    LevelResDto getLevel(Long exp);

    LevelInfoResDto findByLevel(int level);
}
