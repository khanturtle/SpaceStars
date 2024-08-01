package com.spacestar.back.auth.repository;

import com.spacestar.back.auth.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByUuid(String uuid);

    Optional<Member> findByNickname(String nickname);

    @Transactional
    @Modifying
    @Query("update Member m set m.isProfile = :isProfile where m.id = :id")
    void updateIsProfile(@Param("id") Long id, @Param("isProfile") boolean isProfile);

    List<Member> findByNicknameContaining(String nickname);
}
