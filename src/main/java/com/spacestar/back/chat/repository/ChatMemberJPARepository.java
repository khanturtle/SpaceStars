package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ChatMemberJPARepository extends JpaRepository<ChatMember, Long> {

    public List<ChatMember> findAllByMemberUuid(String memberUuid);
}
