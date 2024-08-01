package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    ProfileImage findByUuidAndMain(String uuid, boolean b);

    List<ProfileImage> findAllByUuid(String uuid);

    boolean existsByUuidAndMain(String uuid, boolean b);

    ProfileImage findByUuidAndProfileImageUrl(String uuid, String profileImageUrl);

    @Query(value = "SELECT p FROM ProfileImage p WHERE p.uuid = :uuid ORDER BY p.id DESC LIMIT 1")
    ProfileImage findLastByUuid(@Param("uuid") String uuid);
}
