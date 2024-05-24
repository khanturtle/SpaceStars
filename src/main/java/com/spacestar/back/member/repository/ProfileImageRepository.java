package com.spacestar.back.member.repository;

import com.spacestar.back.member.domain.Member;
import com.spacestar.back.member.domain.ProfileImage;
import com.spacestar.back.member.dto.req.ProfileImageReqDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    List<ProfileImage> findAllByMember(Member member);
}
