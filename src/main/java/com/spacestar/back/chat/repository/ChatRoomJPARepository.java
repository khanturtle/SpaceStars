package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatRoom;
import com.spacestar.back.chat.dto.ChatRoomDto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface ChatRoomJPARepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findByRoomNumber(String roomNumber);


    @Query("select c from ChatRoom c where c.uuid = ?1 and c.receiverUuid = ?2")
    Optional<ChatRoom> findChatRoomByUuids(String uuid, String receiverUuid);

}
