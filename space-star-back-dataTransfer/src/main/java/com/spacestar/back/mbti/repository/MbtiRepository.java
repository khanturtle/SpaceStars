package com.spacestar.back.mbti.repository;

import com.spacestar.back.mbti.domain.Mbti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiRepository extends JpaRepository<Mbti, Long> {
}
