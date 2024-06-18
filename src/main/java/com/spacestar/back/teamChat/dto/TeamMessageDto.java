package com.spacestar.back.teamChat.dto;

import com.spacestar.back.chat.enums.MessageType;
import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TeamMessageDto {
    String roomNumber;
    String senderUuid;
    String content;
    MessageType messageType;
    Instant createdAt;

    public static TeamMessageDto teamChatMessageReqVoToDto(TeamChatMessageReqVo teamChatMessageReqVo, String roomNumber, Instant createdAt){
        return TeamMessageDto.builder()
                .roomNumber(roomNumber)
                .senderUuid(teamChatMessageReqVo.getSenderUuid())
                .content(teamChatMessageReqVo.getContent())
                .messageType(teamChatMessageReqVo.getMessageType())
                .createdAt(createdAt)
                .build();
    }
    public static TeamChatMessageCollection toEntity(TeamMessageDto teamMessageDto){
        return TeamChatMessageCollection.builder()
                .roomNumber(teamMessageDto.getRoomNumber())
                .senderUuid(teamMessageDto.getSenderUuid())
                .content(teamMessageDto.getContent())
                .messageType(teamMessageDto.getMessageType())
                .createdAt(teamMessageDto.getCreatedAt())
                .build();
    }

    public static TeamMessageDto teamMessageDtoFromEntity(TeamChatMessageCollection teamChatMessageCollection){
        return TeamMessageDto.builder()
                .roomNumber(teamChatMessageCollection.getRoomNumber())
                .senderUuid(teamChatMessageCollection.getSenderUuid())
                .content(teamChatMessageCollection.getContent())
                .messageType(teamChatMessageCollection.getMessageType())
                .createdAt(teamChatMessageCollection.getCreatedAt())
                .build();
    }
}


