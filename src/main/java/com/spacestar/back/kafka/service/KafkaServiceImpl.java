package com.spacestar.back.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.spacestar.back.kafka.message.FriendMessage;
import com.spacestar.back.kafka.message.MatchingMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService{

	private final Sinks.Many<MatchingMessage> matchingSink;
	private final Sinks.Many<FriendMessage> friendSink;

	//Matching Topic 에서 메시지를 읽어옴 : Consumer
	@Override
	@KafkaListener(topics = "matching", groupId = "group_1")
	public void MatchingListen(MatchingMessage message){
		log.info("수신 : {}", message);
		matchingSink.tryEmitNext(message);
	}

	@Override
	@KafkaListener(topics = "dev.profile-service.friend-request", groupId = "group_2")
	public void friendListen(FriendMessage friendMessage){
		log.info("수신 : {}", friendMessage);
		friendSink.tryEmitNext(friendMessage);
	}
}
