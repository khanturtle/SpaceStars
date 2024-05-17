package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.gamedetails.domain.GameServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameServerRepository extends JpaRepository<GameServer,Long> {
}
