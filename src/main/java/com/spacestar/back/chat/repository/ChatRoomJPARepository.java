package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomJPARepository extends JpaRepository<ChatRoom, Long> {

}
