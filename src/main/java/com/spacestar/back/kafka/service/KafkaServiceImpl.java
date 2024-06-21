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
	@KafkaListener(topics = "dev.matching-service.match-request", groupId = "group_4")
	public void matchingListen(MatchingMessage message){
		log.info("수신 : {}", message);
		matchingSink.tryEmitNext(message);
	}

	@Override
	@KafkaListener(topics = "dev.profile-service.friend-request", groupId = "group_14")
	public void friendListen(FriendMessage message){
		// log.info("수신 : {}", friendMessage);
		// friendSink.tryEmitNext(friendMessage);

		try{
			log.info("수신 : {}", message);
			friendSink.tryEmitNext(message);
		}catch (Exception e){
			log.error("에러 발생: " , e);
		}

	}
}
