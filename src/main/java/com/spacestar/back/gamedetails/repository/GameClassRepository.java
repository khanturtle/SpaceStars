package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.gamedetails.domain.GameClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameClassRepository extends JpaRepository<GameClass,Long> {
    List<GameClass> findByGame(Game game);
}
