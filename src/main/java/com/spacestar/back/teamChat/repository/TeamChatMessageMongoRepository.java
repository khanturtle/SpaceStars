package com.spacestar.back.teamChat.repository;

import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamChatMessageMongoRepository extends MongoRepository<TeamChatMessageCollection, String>{



}
