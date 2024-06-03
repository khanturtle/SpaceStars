package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ChatRoomJPARepository extends JpaRepository<ChatRoom, Long> {

}
