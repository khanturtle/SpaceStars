package com.spacestar.back.swipe.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.swipe.SwipeStatus;
import com.spacestar.back.swipe.domain.QSwipe;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.spacestar.back.swipe.SwipeStatus.WAIT;

@Component
@RequiredArgsConstructor
public class CustomSwipeRepositoryImpl implements CustomSwipeRepository {

    private final JPAQueryFactory query;

    @Override
    public List<SwipeListResDto> findWaitRequest(String uuid) {
        QSwipe qSwipe = QSwipe.swipe;
        return query
                .select(Projections.constructor(SwipeListResDto.class,
                        qSwipe.matchFromMember))
                .from(qSwipe)
                .where(qSwipe.matchToMember.eq(uuid).and(qSwipe.status.eq(WAIT)))
                .fetch();
    }
}
