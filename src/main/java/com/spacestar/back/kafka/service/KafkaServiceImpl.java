package com.spacestar.back.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.spacestar.back.kafka.message.MatchingMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService{

	private final KafkaTemplate<String, MatchingMessage> kafkaTemplate;

	// 메세지 전송
	@Override
	public void sendMessage(MatchingMessage message){

		kafkaTemplate.send("dev.matching-service.match-request", message);
	}
}
