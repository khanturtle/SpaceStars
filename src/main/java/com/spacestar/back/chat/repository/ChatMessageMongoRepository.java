package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.ChatMessageCollection;
import com.spacestar.back.chat.dto.MessageDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageMongoRepository extends MongoRepository<ChatMessageCollection, String>{
    public List<ChatMessageCollection> findAllByRoomNumber(String roomNumber);
}
