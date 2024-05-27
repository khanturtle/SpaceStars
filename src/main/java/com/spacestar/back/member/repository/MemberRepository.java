package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByUuid(String uuid);

    @Transactional
    @Modifying
    @Query("update Member m set m.swipe = :swipe where m.uuid = :uuid")
    void updateSwipe(@Param("uuid") String uuid, @Param("swipe") boolean swipe);
}
