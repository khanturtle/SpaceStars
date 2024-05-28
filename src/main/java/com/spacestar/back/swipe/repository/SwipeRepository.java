package com.spacestar.back.swipe.repository;

import com.spacestar.back.swipe.domain.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwipeRepository extends JpaRepository<Swipe,Long>,CustomSwipeRepository {

}