package com.spacestar.back.game.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spacestar.back.game.domain.QGame;
import com.spacestar.back.game.dto.res.GameOptionResDto;
import com.spacestar.back.game.dto.res.GameResDto;
import com.spacestar.back.game.dto.res.GameResDto2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class CustomGameRepositoryImpl implements CustomGameRepository {

    private final JPAQueryFactory query;

    @Override
    public List<GameResDto> findAllGameNames() {
        QGame qGame = QGame.game;

        List<GameResDto> gameResDtos = query
                .select(Projections.constructor(GameResDto.class,
                        qGame.name,
                        qGame.gameImage,
                        qGame.nameKor,
                        qGame.gameLogoImage,
                        qGame.id))
                .from(qGame)
                .fetch();
        System.out.println("qGame = " + qGame.gameImage);
        return IntStream.range(0, gameResDtos.size())
                .mapToObj(i -> GameResDto.builder()
                        .index(i)
                        .gameId(gameResDtos.get(i).getGameId())
                        .gameName(gameResDtos.get(i).getGameName())
                        .gameLogoImage(gameResDtos.get(i).getGameLogoImage())
                        .gameNameKor(gameResDtos.get(i).getGameNameKor())
                        .gameImage(gameResDtos.get(i).getGameImage())
                        .build())
                        .toList();
    }

    @Override
    public Optional<GameOptionResDto> findGameOption(Long gameId) {
        QGame qGame = QGame.game;

        return Optional.ofNullable(query
                .select(Projections.constructor(
                        GameOptionResDto.class,
                        qGame.isTier,
                        qGame.isPosition,
                        qGame.isClass,
                        qGame.isServer))
                .from(qGame)
                .where(qGame.id.eq(gameId))
                .fetchOne());
    }

    @Override
    public GameResDto2 findGame(Long gameId) {
        QGame qGame = QGame.game;

        return query.select(Projections.constructor(GameResDto2.class,
                        qGame.name,
                        qGame.gameImage,
                        qGame.nameKor,
                        qGame.gameLogoImage,
                        qGame.id))
                .from(qGame)
                .where(qGame.id.eq(gameId))
                .fetchOne();
    }
}