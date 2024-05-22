package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
