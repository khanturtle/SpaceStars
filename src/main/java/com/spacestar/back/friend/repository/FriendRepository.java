package com.spacestar.back.friend.repository;

import com.spacestar.back.friend.domain.Friend;
import com.spacestar.back.friend.enums.FriendType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUuid(String uuid);

<<<<<<< Updated upstream
=======
    List<Friend> findByUuidAndFriendType(String uuid, FriendType friendType);

    @Query("select f from Friend f where f.friendUuid = :friendUuid and f.uuid = :uuid")
>>>>>>> Stashed changes
    Friend findByFriendUuidAndUuid(String friendUuid, String uuid);

    Optional<Friend> findByUuidAndFriendUuid(String targetUuid, String uuid);
}
