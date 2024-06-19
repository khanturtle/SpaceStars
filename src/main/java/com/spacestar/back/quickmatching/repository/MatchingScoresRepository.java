package com.spacestar.back.quickmatching.repository;

import com.spacestar.back.quickmatching.domain.MatchingScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingScoresRepository extends JpaRepository<MatchingScores,Long> {
    @Query("SELECT m.score FROM MatchingScores m WHERE m.myId = :myId AND m.yourId = :yourId")
    Integer getScore(@Param("myId") long myId, @Param("yourId") long yourId);
}