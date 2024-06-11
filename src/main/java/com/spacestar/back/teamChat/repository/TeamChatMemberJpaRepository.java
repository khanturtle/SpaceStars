package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamChatMemberJpaRepository extends JpaRepository<TeamChatMember, Long> {
}
