package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamChatRoomJpaRepository extends JpaRepository<TeamChatRoom, Long> {

}
