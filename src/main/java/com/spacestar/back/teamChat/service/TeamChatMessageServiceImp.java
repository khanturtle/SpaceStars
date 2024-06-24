package com.spacestar.back.teamChat.service;

import com.spacestar.back.chat.domain.collection.ChatMessageCollection;
import com.spacestar.back.chat.dto.RecentMessageCountDto;
import com.spacestar.back.chat.dto.RecentMessageDto;
import com.spacestar.back.chat.enums.MessageType;
import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import com.spacestar.back.teamChat.dto.RecentTeamMessageCountDto;
import com.spacestar.back.teamChat.dto.RecentTeamMessageDto;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.repository.TeamChatMessageMongoRepository;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    // 읽은 메시지 조회

    @Override
    public List<TeamMessageDto> getReadTeamMessage(String uuid, String roomNumber, Pageable pageable) {
        Optional<TeamChatMessageCollection> optionalExitMessage = teamChatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);
        if(optionalExitMessage.isPresent()){
            TeamChatMessageCollection exitMessage = optionalExitMessage.get();
            Instant exitTime = exitMessage.getCreatedAt();

            // exitTime 이후의 메시지를 찾기
            Page<TeamChatMessageCollection> readMessages = teamChatMessageRepository.findReadTeamMessage(roomNumber, exitTime, pageable);

            // MessageDto 로 변환하여 반환
            return readMessages.stream()
                    .map(TeamMessageDto::teamMessageDtoFromEntity)
                    .toList();
        }
        return Collections.emptyList();
    }

    // 안 읽은 메시지 조회
    @Override
    public List<TeamMessageDto> getUnreadTeamMessage(String uuid, String roomNumber) {
        // MongoDB 에서 roomNumber 와 uuid 에 해당하는 사용자의 마지막 퇴장 메시지를 가져오기
        Optional<TeamChatMessageCollection> optionalExitMessage = teamChatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);
        if(optionalExitMessage.isPresent()){
            TeamChatMessageCollection exitMessage = optionalExitMessage.get();
            Instant exitTime = exitMessage.getCreatedAt();

            // exitTime 이후의 메시지를 찾기
            List<TeamChatMessageCollection> readMessages = teamChatMessageRepository.findUnreadTeamMessage(roomNumber, exitTime);

            // MessageDto로 변환하여 반환
            return readMessages.stream()
                    .map(TeamMessageDto::teamMessageDtoFromEntity)
                    .toList();
        }
        return Collections.emptyList();
    }

    @Override
    public RecentTeamMessageDto getRecentTeamMessage(String uuid, String roomNumber) {

        List<TeamChatMessageCollection> OpRecentTeamMessage = teamChatMessageRepository.findRecentTeamMessage(roomNumber);

        if (OpRecentTeamMessage.isEmpty()) {
            return RecentTeamMessageDto.builder()
                    .senderUuid("")
                    .lastChatMessage("")
                    .createdAt(null)
                    .build();
        }
        TeamChatMessageCollection recentTeamMessage = OpRecentTeamMessage.get(0);
        String lastChatMessage = (recentTeamMessage.getMessageType() == MessageType.TEXT) ?
                recentTeamMessage.getContent() : "사진을 보냈습니다";

        return RecentTeamMessageDto.builder()
                .senderUuid(recentTeamMessage.getSenderUuid())
                .lastChatMessage(lastChatMessage)
                .createdAt(recentTeamMessage.getCreatedAt())
                .build();
    }

    @Override
    public RecentTeamMessageCountDto getRecentMessageCount(String uuid, String roomNumber) {
        Optional<TeamChatMessageCollection> optionalExitMessage = teamChatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);

        if (optionalExitMessage.isEmpty()) {
            return RecentTeamMessageCountDto.builder()
                    .UnReadMessageCount(0)
                    .build();
        }

        TeamChatMessageCollection exitMessage = optionalExitMessage.get();
        Instant exitTime = exitMessage.getExitTime();
        List<TeamChatMessageCollection> unReadMessages = teamChatMessageRepository.findUnreadTeamMessage(roomNumber, exitTime);

        // 999개 이상이면 999개 까지만
        int unReadCount = Math.min(unReadMessages.size(), 999);
        return RecentTeamMessageCountDto.builder()
                .UnReadMessageCount(unReadCount)
                .build();

    }


}















