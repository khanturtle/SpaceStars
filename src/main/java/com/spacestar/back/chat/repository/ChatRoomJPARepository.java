package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ChatRoomJPARepository extends JpaRepository<ChatRoom, Long> {

    public ChatRoom findByRoomNumber(String roomNumber);

}
