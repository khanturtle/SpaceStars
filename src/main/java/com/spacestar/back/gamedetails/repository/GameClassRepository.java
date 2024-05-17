package com.spacestar.back.gamedetails.repository;

import com.spacestar.back.gamedetails.domain.GameClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameClassRepository extends JpaRepository<GameClass,Long> {
}
