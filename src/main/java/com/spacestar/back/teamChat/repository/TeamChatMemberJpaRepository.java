package com.spacestar.back.teamChat.repository;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.dto.ChatRoomDetailDto;
import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChatMemberJpaRepository extends JpaRepository<TeamChatMember, Long> {
    @Query("SELECT m FROM TeamChatMember m JOIN FETCH m.teamChatRoom r WHERE r.id = :id")
    List<TeamChatMember> findByTeamChatRoomId(@Param("id") Long id);

    List<TeamChatMember> findAllByMemberUuid(String uuid);

    TeamChatMember findByTeamChatRoomAndMemberUuid(TeamChatRoom teamChatRoom, String uuid);
}
