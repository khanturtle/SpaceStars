package com.spacestar.back.swipe.repository;

import com.spacestar.back.swipe.domain.Swipe;
import com.spacestar.back.swipe.dto.res.SwipeListResDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwipeRepository extends JpaRepository<Swipe,Long>,CustomSwipeRepository {
    List<Swipe> findByMatchFromMember(String uuid);
}