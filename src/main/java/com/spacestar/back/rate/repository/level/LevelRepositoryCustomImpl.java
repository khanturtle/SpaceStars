package com.spacestar.back.rate.repository.level;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.rate.dto.res.LevelExpResDto;
import com.spacestar.back.rate.dto.res.LevelInfoResDto;
import com.spacestar.back.rate.dto.res.LevelResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.spacestar.back.rate.domain.QLevel.level1;

@Component
@RequiredArgsConstructor
public class LevelRepositoryCustomImpl implements LevelRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public LevelResDto getLevel(Long exp) {

        return query
                .select(Projections.constructor(LevelResDto.class, level1.level))
                .from(level1)
                .where(
                        level1.startExp.loe(exp)
                                .and(level1.endExp.isNull().or(level1.endExp.goe(exp))))
                .fetchOne();
    }

    @Override
    public Integer getStartLevelExp(Long exp) {
        return query
                .select(level1.startExp.as("levelExp"))
                .from(level1)
                .where(
                        level1.startExp.loe(exp)
                                .and(level1.endExp.isNull().or(level1.endExp.goe(exp))))
                .fetchOne();
    }

    @Override
    public LevelInfoResDto findByLevel(int level) {
        return query.select(Projections.constructor(LevelInfoResDto.class,
                        level1.levelIcon,
                        level1.endExp.subtract(level1.startExp).as("levelTotalExp")))
                .from(level1)
                .where(level1.level.eq(level))
                .fetchOne();
    }
}
