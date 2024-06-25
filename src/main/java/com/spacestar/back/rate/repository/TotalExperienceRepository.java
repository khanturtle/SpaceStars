package com.spacestar.back.rate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spacestar.back.rate.domain.TotalExperience;

public interface TotalExperienceRepository extends JpaRepository<TotalExperience, Long> {

	Optional<TotalExperience> findByUuid(String uuid);
}
