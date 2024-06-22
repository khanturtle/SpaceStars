package com.spacestar.back.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.spacestar.back.kafka.message.FriendMessage;
import com.spacestar.back.kafka.message.MatchingMessage;

import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

	private final KafkaProperties kafkaProperties;

	//공통 설정
	private Map<String, Object> commonConsumerConfig(){
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return props;
	}

	// 매칭 메세지 컨슈머 팩토리 생성
	@Bean
	public ConsumerFactory<String, MatchingMessage> matchingMessageConsumerFactory(){

		return new DefaultKafkaConsumerFactory<>(
				commonConsumerConfig(),
				new StringDeserializer(),
				new JsonDeserializer<>(MatchingMessage.class)
		);
	}

	// 매칭 메시지 리스너 컨테이너 팩토리
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MatchingMessage> matchingMessageKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MatchingMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(matchingMessageConsumerFactory());
		return factory;
	}

	// 친구 요청 메시지 컨슈머 팩토리 생성
	@Bean
	public ConsumerFactory<String, FriendMessage> friendMessageConsumerFactory(){

		return new DefaultKafkaConsumerFactory<>(
				commonConsumerConfig(),
				new StringDeserializer(),
				new JsonDeserializer<>(FriendMessage.class)
		);
	}

	// 친구 요청 메시지 리스너 컨테이너 팩토리
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FriendMessage> friendMessageKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FriendMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(friendMessageConsumerFactory());
		return factory;
	}

	@Bean
	public Sinks.Many<MatchingMessage> matchingSink(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}

	@Bean
	public Sinks.Many<FriendMessage> friendSink(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}
}
