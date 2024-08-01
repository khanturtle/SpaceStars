package com.spacestar.voice.signaling.domain.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//@RequestMapping("/signaling")
public class signalingController {

    /**
     * offer 정보를 주고 받기 위한 websocket
     * camKey : 각 요청하는 캠의 key , roomNumber : 룸 아이디
     */
    @MessageMapping("/peer/offer/{camKey}/{roomNumber}")
    @SendTo("/sub/peer/offer/{camKey}/{roomNumber}")
    public String PeerHandleOffer(@Payload String offer, @DestinationVariable(value = "roomNumber") String roomNumber,
                                  @DestinationVariable(value = "camKey") String camKey) {
        log.info("[OFFER] {} : {}", camKey, offer);
        return offer;
    }

    /**
     * iceCandidate 정보를 주고 받기 위한 webSocket
     * camKey : 각 요청하는 캠의 key , roomNumber : 룸 아이디
     */

    @MessageMapping("/peer/iceCandidate/{camKey}/{roomNumber}")
    @SendTo("/sub/peer/iceCandidate/{camKey}/{roomNumber}")
    public String PeerHandleIceCandidate(@Payload String candidate, @DestinationVariable(value = "roomNumber") String roomNumber,
                                         @DestinationVariable(value = "camKey") String camKey) {
        log.info("[ICECANDIDATE] {} : {}", camKey, candidate);
        return candidate;
    }

    @MessageMapping("/peer/answer/{camKey}/{roomNumber}")
    @SendTo("/sub/peer/answer/{camKey}/{roomNumber}")
    public String PeerHandleAnswer(@Payload String answer, @DestinationVariable(value = "roomNumber") String roomNumber,
                                   @DestinationVariable(value = "camKey") String camKey) {
        log.info("[ANSWER] {} : {}", camKey, answer);
        return answer;
    }

    //camKey 를 받기위해 신호를 보내는 webSocket
    @MessageMapping("/call/key")
    @SendTo("/sub/call/key")
    public String callKey(@Payload String message) {
        log.info("[Key] : {}", message);
        return message;
    }

    //자신의 camKey 를 모든 연결된 세션에 보내는 webSocket
    @MessageMapping("/send/key")
    @SendTo("/sub/send/key")
    public String sendKey(@Payload String message) {
        return message;
    }


}
