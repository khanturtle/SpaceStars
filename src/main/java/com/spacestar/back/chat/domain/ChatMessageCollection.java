package com.spacestar.back.chat.domain;

import com.spacestar.back.chat.enums.MessageType;
import com.spacestar.back.global.GlobalCreateTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@NoArgsConstructor
@Document(collection = "chat_message")
@EntityListeners(AuditingEntityListener.class)
public class ChatMessageCollection {
    @Id
    private String id;
    private String roomNumber;
    private String senderUuid;
    private String content;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Builder
    public ChatMessageCollection(String id, String roomNumber, String senderUuid, String content, Date createdAt, MessageType messageType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.senderUuid = senderUuid;
        this.content = content;
        this.createdAt = createdAt;
        this.messageType = messageType;
    }
}
