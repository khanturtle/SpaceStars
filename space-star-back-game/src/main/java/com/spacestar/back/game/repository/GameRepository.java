package com.spacestar.back.game.repository;

import com.spacestar.back.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long>,CustomGameRepository{
}