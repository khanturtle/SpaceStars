package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.gamedetails.domain.GamePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePositionRepository extends JpaRepository<GamePosition,Long> {
}
