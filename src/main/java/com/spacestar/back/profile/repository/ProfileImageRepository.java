package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    @Query("select p from ProfileImage p where p.uuid = :uuid and p.main = :main")
    ProfileImage findByUuidAndMain(@Param("uuid") String uuid, @Param("main") boolean b);

    List<ProfileImage> findAllByUuid(String uuid);

    boolean existsByUuidAndMain(String uuid, boolean b);

    ProfileImage findByUuidAndProfileImageUrl(String uuid, String profileImageUrl);
}
