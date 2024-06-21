package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
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
public interface ChatMessageMongoRepository extends MongoRepository<ChatMessageCollection, String>{

    // exit 이전만 받게 추가 해야 할듯
    @Query("{ roomNumber: ?0, content: {$exists: true} }")
    List<ChatMessageCollection> findAllByRoomNumber(String roomNumber);

    @Query("{ roomNumber: ?0, senderUuid: ?1, exitTime: { $exists: true } }")
    List<ChatMessageCollection> findExitByRoomNumber(String roomNumber,String senderUuid, Sort sort);



    default Optional<ChatMessageCollection> findLatestExitByRoomNumber(String roomNumber, String senderUuid) {
        List<ChatMessageCollection> results = findExitByRoomNumber(roomNumber, senderUuid, Sort.by(Sort.Direction.DESC, "exitTime"));
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));

    }
    @Query("{ roomNumber: ?0, createdAt: { $gt: ?1 } }")
    List<ChatMessageCollection> findUnreadMessage(String roomNumber , Instant exitTime);

    @Query("{ roomNumber: ?0, createdAt: { $lt: ?1 } }")
    Page<ChatMessageCollection> findReadMessage(String roomNumber , Instant exitTime, Pageable pageable);

    @Query(value="{ 'roomNumber' : ?0 }", sort="{ 'createdAt' : -1 }")
    Optional<ChatMessageCollection> findRecentMessage(String roomNumber);
}
