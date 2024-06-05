package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUuid(String uuid);

    @Transactional
    @Modifying
    @Query("update Profile p set p.swipe = :swipe where p.uuid = :uuid")
    void updateSwipe(@Param("uuid") String uuid, @Param("swipe") boolean swipe);
}
