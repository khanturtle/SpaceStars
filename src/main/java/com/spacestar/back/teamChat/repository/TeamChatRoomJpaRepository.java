package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamChatRoomJpaRepository extends JpaRepository<TeamChatRoom, Long> {

}
