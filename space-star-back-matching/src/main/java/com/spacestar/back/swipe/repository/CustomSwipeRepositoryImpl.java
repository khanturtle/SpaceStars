package com.spacestar.back.swipe.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import static com.spacestar.back.swipe.domain.SwipeStatus.*;
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
    public void agreeRequest(String uuid, String fromMemberUuid) {
        query.update(swipe)
                .where(swipe.matchToMember.eq(uuid).and(swipe.matchFromMember.eq(fromMemberUuid)))
                .set(swipe.status, AGREE)
                .execute();
    }

    @Transactional
    @Override
    public void rejectRequest(String uuid, String fromMemberUuid) {
        query.update(swipe)
                .where(swipe.matchToMember.eq(uuid).and(swipe.matchFromMember.eq(fromMemberUuid)))
                .set(swipe.status, REJECT)
                .execute();
    }

    @Transactional
    @Override
    public void deleteExpiredSwipe() {
        query.delete(swipe)
                .where(swipe.createdAt.before(LocalDateTime.now().minusDays(3)))
                .execute();
    }
  
    public int countSwipe(String uuid) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        return (int) query.selectFrom(swipe)
                .where(swipe.createdAt.between(startOfDay, endOfDay)
                        .and(swipe.matchFromMember.eq(uuid)))
                .fetchCount();
    }
}
