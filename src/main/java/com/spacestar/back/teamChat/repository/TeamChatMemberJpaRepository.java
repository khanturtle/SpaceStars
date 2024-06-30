package com.spacestar.back.teamChat.repository;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.dto.ChatRoomDetailDto;
import com.spacestar.back.chat.enums.ParticipationType;
import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamChatMemberJpaRepository extends JpaRepository<TeamChatMember, Long> {
    @Query("SELECT m FROM TeamChatMember m JOIN FETCH m.teamChatRoom r WHERE r.id = :id")
    List<TeamChatMember> findByTeamChatRoomId(@Param("id") Long id);

    @Query("SELECT m FROM TeamChatMember m WHERE m.memberUuid = :uuid and m.teamParticipationType = 'JOINED'")
    List<TeamChatMember> findAllByMemberUuid(String uuid);

    @Query("SELECT m FROM TeamChatMember m JOIN FETCH m.teamChatRoom r WHERE r.id = :id AND m.memberUuid = :uuid")
    TeamChatMember findByMemberUuidAndId(String uuid,Long id);

    @Query("SELECT m FROM TeamChatMember m JOIN FETCH m.teamChatRoom r " +
            "WHERE r.id = :id AND m.teamParticipationType = :participationType")
    List<TeamChatMember> findCurrentMembersInChatRoom(@Param("id") Long id, @Param("participationType") TeamParticipationType participationType);


    Optional<TeamChatMember> findByTeamChatRoomAndMemberUuid(TeamChatRoom teamChatRoom, String uuid);
}
