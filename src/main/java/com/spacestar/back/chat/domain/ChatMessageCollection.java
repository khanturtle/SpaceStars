package com.spacestar.back.chat.domain;

import com.spacestar.back.chat.enums.MessageType;
import com.spacestar.back.global.GlobalCreateTime;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "chat_message")
@EntityListeners(AuditingEntityListener.class)
public class ChatMessageCollection extends GlobalCreateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomNumber;
    private String senderUuid;
    private String content;
    private MessageType messageType;

    @Builder
    public ChatMessageCollection(Long id, String roomNumber, String senderUuid, String content, MessageType messageType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.senderUuid = senderUuid;
        this.content = content;
        this.messageType = messageType;
    }
}
