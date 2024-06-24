package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
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
        return null;
    }

}















