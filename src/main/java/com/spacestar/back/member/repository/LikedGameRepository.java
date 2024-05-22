package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.LikedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedGameRepository extends JpaRepository<LikedGame, Long> {
}
