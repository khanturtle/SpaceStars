package com.spacestar.back.kafka.service;

import com.spacestar.back.kafka.message.FriendMessage;
import com.spacestar.back.kafka.message.MatchingMessage;

public interface KafkaService {

	//Matching Topic 메시지를 읽어옴 : Consumer
	void MatchingListen(MatchingMessage matchingMessageVo);

	void friendListen(FriendMessage friendMessage);
}
