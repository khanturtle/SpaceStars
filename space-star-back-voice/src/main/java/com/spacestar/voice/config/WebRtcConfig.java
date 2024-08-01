package com.spacestar.voice.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
    public class WebRtcConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/pub"); // send url 설정
        registry.enableSimpleBroker("/sub"); //broker url 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // 소켓연결을 위한 endpoint
        registry.addEndpoint("/api/v1/wsvoice") // websocket 접속시 endpoint 설정
                .setAllowedOriginPatterns("*"); //cors 허용
        registry.addEndpoint("/api/v1/wsvoice") // websocket 접속시 endpoint 설정
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}