package com.spacestar.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebRtcConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 구독주소 주소를 구독한 클라이언트는 브로드캐스팅 메시지 수신가능
        registry.enableSimpleBroker("/topic"); //broker url 설정
        // 송신주소 클라이언트는 송신주소를 통해 메세지를 서버로 전송
        registry.setApplicationDestinationPrefixes("/app"); // send url 설정
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // 소켓연결을 위한 endpoint
        registry.addEndpoint("/api/chat") // websocket 접속시 endpoint 설정
                .setAllowedOriginPatterns("*") //cors 허용
                .withSockJS(); // 브라우저에서 WebSocket을 지원하지 않을때 대체방법으로 SockJS 사용
    }
}
