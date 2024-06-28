package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamChatRoomJpaRepository extends JpaRepository<TeamChatRoom, Long> {

    TeamChatRoom findByRoomNumber(String roomNumber);

    //teamChatRoom 으로 멤버찾고 갯수
    @Query("select count(t) from TeamChatMember t where t.teamChatRoom = :teamChatRoom and t.teamParticipationType = 'JOINED'")
    int findCountMembersByTeamChatRoom(@Param("teamChatRoom") TeamChatRoom teamChatRoom);

    //roomnumber와 uuid로 속한방이 있는지 없는지 boolean
    @Query("select count(t) > 0 from TeamChatMember t where t.teamChatRoom.roomNumber = :roomNumber and t.memberUuid = :memberUuid")
    boolean existsByRoomNumberAndMemberUuid(@Param("roomNumber") String roomNumber, @Param("memberUuid") String memberUuid);

}
