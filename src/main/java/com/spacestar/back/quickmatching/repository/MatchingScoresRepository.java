package com.spacestar.back.quickmatching.repository;

import com.spacestar.back.quickmatching.domain.MatchingScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingScoresRepository extends JpaRepository<MatchingScores,Long> {
}