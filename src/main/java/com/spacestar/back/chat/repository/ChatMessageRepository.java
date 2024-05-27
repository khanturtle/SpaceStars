package com.spacestar.back.chat.repository;

import com.spacestar.back.chat.domain.ChatMessageCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessageCollection, String>{

}
