package com.spacestar.back.teamChat.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.chat.domain.entity.QChatRoom;
import com.spacestar.back.teamChat.domain.entity.QTeamChatRoom;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomTeamChatRepositoryImpl implements CustomTeamChatRepository{
    private final JPAQueryFactory jpaQueryFactory;
    QTeamChatRoom teamChatRoom = QTeamChatRoom.teamChatRoom;
    @Override
    public List<TeamChatRoom> findQueryDslTeamChatRoom(Long gameId, Integer maxMembers, Boolean isFinished) {
        return jpaQueryFactory.selectFrom(teamChatRoom)
                .where(eqGameId(gameId), eqMaxMembers(maxMembers), eqIsFinished(isFinished))
                .fetch();
    }

    private BooleanExpression eqGameId(Long gameId) {
        if (gameId == null) {
            return null;
        }
        return teamChatRoom.gameId.eq(gameId);
    }

    private BooleanExpression eqMaxMembers(Integer maxMembers) {
        return (maxMembers != null) ? teamChatRoom.maxMembers.eq(maxMembers) : null;
    }


    private BooleanExpression eqIsFinished(Boolean isFinished) {
        if (isFinished == null) {
            return null;
        }
        return teamChatRoom.isFinished.eq(isFinished);
    }


}
