package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.repository.TeamChatMessageMongoRepository;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TeamChatMessageServiceImp implements TeamChatMessageService{
    private final TeamChatMessageMongoRepository teamChatMessageRepository;


    @Override
    public void addTeamChatMessage(TeamMessageDto teamMessageDto) {
        teamChatMessageRepository.save(TeamMessageDto.toEntity(teamMessageDto));
    }

    @Override
    public TeamMessageDto teamMessageToDto(TeamChatMessageReqVo teamChatMessageReqVo, String roomNumber) {
        Instant createdAt = Instant.now();
        return TeamMessageDto.teamChatMessageReqVoToDto(teamChatMessageReqVo, roomNumber, createdAt);
    }



    @Override
    public void addTeamChatJoin(String roomNumber, String senderUuid) {
        Instant enterTime = Instant.now();
        teamChatMessageRepository.save(TeamChatMessageCollection.builder()
                .roomNumber(roomNumber)
                .senderUuid(senderUuid)
                .createdAt(enterTime)
                .build());
    }

    @Override
    public void addTeamChatExit(String roomNumber, String senderUuid) {
        Instant exitTime = Instant.now();
        teamChatMessageRepository.save(TeamChatMessageCollection.builder()
                .roomNumber(roomNumber)
                .senderUuid(senderUuid)
                .createdAt(exitTime)
                .build());
    }

}















