package com.spacestar.back.rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spacestar.back.rate.domain.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
