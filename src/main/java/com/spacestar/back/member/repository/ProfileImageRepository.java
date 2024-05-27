package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.Member;
import com.spacestar.back.member.domain.ProfileImage;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    List<ProfileImage> findAllByMember(Member member);

    @Query("select p from ProfileImage p where p.member = :member and p.main = :main")
    ProfileImage findByMemberAndMain(@Param("member") Member member, @Param("main") boolean b);

    @Transactional
    @Modifying
    @Query("delete from ProfileImage p where p.member = :member")
    void deleteAllByMember(@Param("member") Member member);
}
