package com.spacestar.back.game.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.game.domain.QGame;
import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.vo.res.GameResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomGameRepositoryImpl implements CustomGameRepository {

    private final JPAQueryFactory query;

    @Override
    public List<GameResVo> findAllGameNames() {
        QGame qGame = QGame.game;

        return query
                .select(Projections.constructor(GameResVo.class, Expressions.constant(0), qGame.name, qGame.image))
                .from(qGame)
                .fetch();
    }

    @Override
    public GameOptionResDto findGameOption(Long gameId) {
        QGame qGame = QGame.game;

        return query
                .select(Projections.constructor(
                        GameOptionResDto.class,
                        qGame.is_tier,
                        qGame.is_position,
                        qGame.is_class,
                        qGame.is_server))
                .from(qGame)
                .where(qGame.id.eq(gameId))
                .fetchOne();
    }
}