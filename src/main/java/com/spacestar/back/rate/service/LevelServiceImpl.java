package com.spacestar.back.rate.service;

import com.spacestar.back.rate.domain.Level;
import com.spacestar.back.rate.domain.TotalExperience;
import com.spacestar.back.rate.dto.res.LevelInfoResDto;
import com.spacestar.back.rate.dto.res.LevelResDto;
import com.spacestar.back.rate.repository.level.LevelRepository;
import com.spacestar.back.rate.repository.TotalExperienceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService{
    private final LevelRepository levelRepository;
    private final TotalExperienceRepository totalExperienceRepository;

    @Override
    public LevelResDto getLevel(String uuid) {
        //없으면 null로 저장
        TotalExperience totalExperience = totalExperienceRepository.findByUuid(uuid).orElse(null);
        if(totalExperience==null){
            return LevelResDto.builder().level(1).build();
        }
        return levelRepository.getLevel(totalExperience.getExp());
    }

    @Override
    public LevelInfoResDto getLevelInfo(int level) {
       return levelRepository.findByLevel(level);
    }
}
