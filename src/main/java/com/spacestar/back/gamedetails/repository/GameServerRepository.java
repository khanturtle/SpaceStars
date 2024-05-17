package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.game.domain.Game;
import com.spacestar.back.gamedetails.domain.GameServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameServerRepository extends JpaRepository<GameServer,Long> {
    List<GameServer> findByGame(Game game);
}
