package com.spacestar.back.swipe.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.spacestar.back.swipe.SwipeStatus.*;
import static com.spacestar.back.swipe.domain.QSwipe.swipe;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class CustomSwipeRepositoryImpl implements CustomSwipeRepository {

    private final JPAQueryFactory query;

    @Override
    public List<SwipeListResDto> findWaitRequest(String uuid) {

        return query
                .select(Projections.constructor(SwipeListResDto.class,
                        swipe.matchFromMember))
                .from(swipe)
                .where(swipe.matchToMember.eq(uuid).and(swipe.status.eq(WAIT)))
                .fetch();
    }
    @Override
    public List<SwipeListResDto> findSentRequest(String uuid) {

        return query
                .select(Projections.constructor(SwipeListResDto.class,
                        swipe.matchToMember))
                .from(swipe)
                .where(swipe.matchFromMember.eq(uuid).and(swipe.status.eq(WAIT)))
                .fetch();
    }

    @Transactional
    @Override
    public void agreeRequest(String uuid) {
        query.update(swipe)
                .where(swipe.matchToMember.eq(uuid))
                .set(swipe.status, AGREE)
                .execute();
    }

    @Transactional
    @Override
    public void rejectRequest(String uuid) {
        query.update(swipe)
                .where(swipe.matchToMember.eq(uuid))
                .set(swipe.status, REJECT)
                .execute();
    }
}
