package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.PlayGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayGameRepository extends JpaRepository<PlayGame, Long> {
}
