package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface ChatMessageMongoRepository extends MongoRepository<ChatMessageCollection, String>{

    // exit 이전만 받게 추가 해야 할듯
    @Query("{ roomNumber: ?0, content: {$exists: true} }")
    public List<ChatMessageCollection> findAllByRoomNumber(String roomNumber);

    @Query("{ roomNumber: ?0, senderUuid: ?1, exitTime: { $exists: true } }")
    public List<ChatMessageCollection> findExitByRoomNumber(String roomNumber,String senderUuid, Sort sort);

    default ChatMessageCollection findLatestExitByRoomNumber(String roomNumber, String senderUuid) {
        List<ChatMessageCollection> results = findExitByRoomNumber(roomNumber, senderUuid, Sort.by(Sort.Direction.DESC, "exitTime"));
        return results.isEmpty() ? null : results.get(0);

    }
    @Query("{ roomNumber: ?0, createdAt: { $gt: ?1 } }")
    List<ChatMessageCollection> findUnreadMessage(String roomNumber , Instant exitTime);
}
