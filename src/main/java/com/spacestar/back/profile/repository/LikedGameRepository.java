package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.LikedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LikedGameRepository extends JpaRepository<LikedGame, Long> {

    @Transactional
    @Modifying
    @Query("delete from LikedGame l where l.uuid = :uuid")
    void deleteAllByUuid(@Param("uuid") String uuid);
}
