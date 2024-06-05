package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUuid(String uuid);
}
