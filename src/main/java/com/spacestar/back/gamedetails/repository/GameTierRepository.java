package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.gamedetails.domain.GameTier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTierRepository extends JpaRepository<GameTier,Long> {
}
