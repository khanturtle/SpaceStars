package com.spacestar.back.swipe.repository;

import com.spacestar.back.swipe.domain.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SwipeRepository extends JpaRepository<Swipe,Long>,CustomSwipeRepository {

    @Query("SELECT s FROM Swipe s WHERE s.matchFromMember = :uuid AND s.status = 'WAIT'")
    List<Swipe> findByMatchFromMember(String uuid);
}