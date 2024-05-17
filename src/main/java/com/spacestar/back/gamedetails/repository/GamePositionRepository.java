package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.gamedetails.domain.GamePosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamePositionRepository extends JpaRepository<GamePosition,Long> {
    List<GamePosition> findByGame(Game game);
}
