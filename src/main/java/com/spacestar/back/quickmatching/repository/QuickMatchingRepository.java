package com.spacestar.back.quickmatching.repository;

import com.spacestar.back.quickmatching.domain.QuickMatching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuickMatchingRepository extends JpaRepository<QuickMatching,Long> {
}
