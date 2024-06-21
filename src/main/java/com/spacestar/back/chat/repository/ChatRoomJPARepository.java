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

    @Query("select c from ChatRoom c join c.chatMembers m1 join c.chatMembers m2 " +
            "where m1.memberUuid = ?1 and m2.memberUuid = ?2 and m1.chatRoom = m2.chatRoom")
    Optional<ChatRoom> findChatRoomByUuids(String uuid, String receiverUuid);

}
