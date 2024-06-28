package com.spacestar.back.rate.service;

import com.spacestar.back.rate.domain.Level;
import com.spacestar.back.rate.domain.TotalExperience;
import com.spacestar.back.rate.dto.res.LevelExpResDto;
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

    //사용자의 레벨 조회
    @Override
    public LevelResDto getLevel(String uuid) {
        //없으면 null로 저장
        TotalExperience totalExperience = totalExperienceRepository.findByUuid(uuid).orElse(null);
        if(totalExperience==null){
            return LevelResDto.builder().level(1).build();
        }
        return levelRepository.getLevel(totalExperience.getExp());
    }
    //해당 레벨에서 보유중인 경험치 계산
    @Override
    public LevelExpResDto getLevelExp(String uuid) {

        TotalExperience totalExperience = totalExperienceRepository.findByUuid(uuid).orElse(null);

        if(totalExperience==null){
            return LevelExpResDto.builder().levelExp(0).build();
        }

        long exp = totalExperience.getExp();
        return LevelExpResDto.builder()
                .levelExp((int) exp - levelRepository.getStartLevelExp(exp))
                .build();
    }
    //레벨로 레벨 정보 상세 조회
    @Override
    public LevelInfoResDto getLevelInfo(int level) {
        return levelRepository.findByLevel(level);
    }
}
