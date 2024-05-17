package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.gamedetails.domain.GameTier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameTierRepository extends JpaRepository<GameTier,Long> {
    List<GameTier> findByGame(Game game);
}
