package com.spacestar.back.rate.repository.level;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.rate.domain.QLevel;
import com.spacestar.back.rate.dto.res.LevelResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import static com.spacestar.back.rate.domain.QLevel.level1;
import static com.spacestar.back.rate.domain.QTotalExperience.totalExperience;
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
                                .and(level1.endExp.isNull().or(level1.endExp.goe(exp)))
                )
                .fetchOne();

    }
}
