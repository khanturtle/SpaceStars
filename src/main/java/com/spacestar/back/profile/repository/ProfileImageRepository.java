package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    List<ProfileImage> findAllByProfile(Profile profile);

    @Query("select p from ProfileImage p where p.profile = :profile and p.main = :main")
    ProfileImage findByProfileAndMain(@Param("profile") Profile profile, @Param("main") boolean b);
}
