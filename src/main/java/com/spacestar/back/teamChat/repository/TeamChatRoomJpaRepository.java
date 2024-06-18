package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamChatRoomJpaRepository extends JpaRepository<TeamChatRoom, Long> {

    TeamChatRoom findByRoomNumber(String roomNumber);

    //teamChatRoom 으로 멤버찾고 갯수
    @Query("select count(t) from TeamChatMember t where t.teamChatRoom = :teamChatRoom")
    int findCountMembersByTeamChatRoom(TeamChatRoom teamChatRoom);
}
