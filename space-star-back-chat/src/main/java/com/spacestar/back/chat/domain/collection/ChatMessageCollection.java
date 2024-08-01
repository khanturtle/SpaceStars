package com.spacestar.back.chat.domain.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spacestar.back.chat.enums.MessageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Getter
@NoArgsConstructor
@Document(collection = "chat_message")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessageCollection {
    @Id
    private String id;
    private String roomNumber;
    private String senderUuid;
    private String content;
    private Instant createdAt;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    // 들어간 시간 나간시간 추가
    private Instant enterTime;
    private Instant exitTime;


    @Builder
    public ChatMessageCollection(String id, String roomNumber, String senderUuid, String content, Instant createdAt, MessageType messageType,
                                 Instant enterTime, Instant exitTime) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.senderUuid = senderUuid;
        this.content = content;
        this.createdAt = createdAt;
        this.messageType = messageType;
        // roomNumber, senderUuid, 1.enter 2 exit,
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }
}
