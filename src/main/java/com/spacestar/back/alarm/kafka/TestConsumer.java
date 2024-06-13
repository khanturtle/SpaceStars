package com.spacestar.back.alarm.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestConsumer {

	@KafkaListener(topics = "test_topic2", groupId = "group_1")
	public void listener(String message){
		log.info("receive message: " + message);
	}
}
