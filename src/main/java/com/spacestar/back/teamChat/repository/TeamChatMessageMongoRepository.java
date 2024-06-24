package com.spacestar.back.teamChat.repository;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeamChatMessageMongoRepository extends MongoRepository<TeamChatMessageCollection, String>{

    @Query("{ roomNumber: ?0, content: {$exists: true} }")
    List<TeamChatMessageCollection> findExitByRoomNumber(String roomNumber, String senderUuid, Sort sort);


    default Optional<TeamChatMessageCollection> findLatestExitByRoomNumber(String roomNumber, String senderUuid) {
        List<TeamChatMessageCollection> results = findExitByRoomNumber(roomNumber, senderUuid, Sort.by(Sort.Direction.DESC, "exitTime"));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));

    }
    @Query("{ roomNumber: ?0, createdAt: { $gt: ?1 } }")
    List<TeamChatMessageCollection> findUnreadTeamMessage(String roomNumber , Instant exitTime);

    @Query("{ roomNumber: ?0, createdAt: { $lt: ?1 } }")
    Page<TeamChatMessageCollection> findReadTeamMessage(String roomNumber , Instant exitTime, Pageable pageable);

    @Query(value = "{ 'roomNumber' : ?0, 'content': { $exists: true } }", sort = "{ 'createdAt' : -1 }")
    List<TeamChatMessageCollection> findRecentTeamMessage(String roomNumber);

}
