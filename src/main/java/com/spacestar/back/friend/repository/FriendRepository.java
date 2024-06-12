package com.spacestar.back.friend.repository;

import com.spacestar.back.friend.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUuid(String uuid);

    Friend findByFriendUuidAndUuid(String friendUuid, String uuid);
}
