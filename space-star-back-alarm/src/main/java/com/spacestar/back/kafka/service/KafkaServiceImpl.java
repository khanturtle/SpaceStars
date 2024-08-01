package com.spacestar.back.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.spacestar.back.alarm.dto.req.AlarmAddReqDto;
import com.spacestar.back.alarm.repository.AlarmMongoRepository;
import com.spacestar.back.alarm.service.AlarmServiceImpl;
import com.spacestar.back.kafka.message.FriendMessage;
import com.spacestar.back.kafka.message.MatchingMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

	private final Sinks.Many<MatchingMessage> matchingSink;
	private final Sinks.Many<FriendMessage> friendSink;
	private final AlarmMongoRepository alarmMongoRepository;

	@Override
	@KafkaListener(topics = "dev.matching-service.match-request", groupId = "matching_4",
			containerFactory = "matchingMessageKafkaListenerContainerFactory")
	public void matchingListen(MatchingMessage message) {
		log.info("수신 : {}", message);
		alarmMongoRepository.save(AlarmAddReqDto.toEntitySSE(message));
		matchingSink.tryEmitNext(message);
	}

	@Override
	@KafkaListener(topics = "dev.profile-service.friend-request", groupId = "friend_4",
			containerFactory = "friendMessageKafkaListenerContainerFactory")
	public void friendListen(FriendMessage message) {
		log.info("수신 : {}", message);
		alarmMongoRepository.save(AlarmAddReqDto.toEntitySSE(message));
		friendSink.tryEmitNext(message);
	}
}
