package com.spacestar.back.kafka.service;

import com.spacestar.back.kafka.message.MatchingMessage;

public interface KafkaService {

	void sendMessage(MatchingMessage message);
}
