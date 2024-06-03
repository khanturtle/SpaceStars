package com.spacestar.back.profile.repository;

import com.spacestar.back.profile.domain.PlayGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlayGameRepository extends JpaRepository<PlayGame, Long> {

    @Transactional
    @Modifying
    @Query("delete from PlayGame l where l.uuid = :uuid")
    void deleteAllByUuid(@Param("uuid") String uuid);
}
