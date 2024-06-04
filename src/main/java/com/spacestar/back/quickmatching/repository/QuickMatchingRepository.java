package com.spacestar.back.quickmatching.repository;

import com.spacestar.back.quickmatching.domain.QuickMatching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuickMatchingRepository extends JpaRepository<QuickMatching,Long> {
    List<QuickMatching> findByMatchFromMember(String matchFromMember);
}