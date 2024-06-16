package com.spacestar.back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub");       //클라이언트에서 보낸 메세지를 받을 prefix
        registry.enableSimpleBroker("/sub");    //해당 주소를 구독하고 있는 클라이언트들에게 메세지 전달
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/v1/wschat")   //SockJS 연결 주소
                .setAllowedOriginPatterns("*");  //CORS 허용
        registry.addEndpoint("/api/v1/wschat")   //SockJS 연결 주소
                .setAllowedOriginPatterns("*")  //CORS 허용
                .withSockJS();
    }

}
