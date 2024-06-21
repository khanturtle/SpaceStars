package com.spacestar.back.profile.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.profile.dto.res.SwipeMemberInfoResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.spacestar.back.profile.domain.QProfile.profile;
import static com.spacestar.back.profile.domain.QPlayGame.playGame;


import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Component
@RequiredArgsConstructor
public class CustomProfileRepositoryImpl implements CustomProfileRepository{
    private final JPAQueryFactory query;
    @Override
    public List<SwipeMemberInfoResDto> findWaitRequest() {

        return query
                .select(profile, playGame.gameId)
                .from(profile)
                .join(playGame).on(profile.uuid.eq(playGame.uuid))
                .where(profile.swipe.isTrue())
                .fetch()
                .stream()
                .map(tuple -> SwipeMemberInfoResDto.toDto(
                        tuple.get(profile),
                        tuple.get(playGame.gameId)
                ))
                .collect(Collectors.toList());
    }
}
