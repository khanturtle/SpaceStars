package com.spacestar.back.rate.service;

import com.spacestar.back.rate.dto.res.LevelInfoResDto;
import com.spacestar.back.rate.dto.res.LevelResDto;

public interface LevelService {
    LevelResDto getLevel(String uuid);

    LevelInfoResDto getLevelInfo(int level);
}
