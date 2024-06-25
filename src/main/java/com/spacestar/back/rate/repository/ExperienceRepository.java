package com.spacestar.back.rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spacestar.back.rate.domain.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
