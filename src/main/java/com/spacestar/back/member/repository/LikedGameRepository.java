package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.LikedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LikedGameRepository extends JpaRepository<LikedGame, Long> {
    List<LikedGame> findByUuid(String uuid);

    Optional<LikedGame> findByGameIdAndUuid(Long ids, String uuid);

    @Transactional
    @Modifying
    @Query("delete from LikedGame l where l.uuid = :uuid")
    void deleteAllByUuid(@Param("uuid") String uuid);

    List<LikedGame> findAllByUuid(String uuid);
}
