package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatMember;
import com.spacestar.back.chat.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatMemberJPARepository extends JpaRepository<ChatMember, Long> {

    public List<ChatMember> findAllByMemberUuid(String memberUuid);

    public List<ChatMember> findAllByChatRoom(ChatRoom chatRoom);
}
