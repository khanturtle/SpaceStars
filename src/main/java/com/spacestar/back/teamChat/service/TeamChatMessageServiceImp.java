package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.collection.TeamChatMessageCollection;
import com.spacestar.back.teamChat.dto.TeamMessageDto;
import com.spacestar.back.teamChat.repository.TeamChatMessageMongoRepository;
import com.spacestar.back.teamChat.vo.req.TeamChatMessageReqVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    //// MongoDB에서 roomNumber와 uuid에 해당하는 사용자의 마지막 퇴장 메시지를 가져오기
    //        Optional<ChatMessageCollection> optionalExitMessage = chatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);
    //
    //        log.info("optionalExitMessage: {}", optionalExitMessage);
    //
    //        if (optionalExitMessage.isPresent()) {
    //            ChatMessageCollection exitMessage = optionalExitMessage.get();
    //            Instant exitTime = exitMessage.getExitTime();
    //
    //            // exitTime 이후의 메시지를 찾기
    //            Page<ChatMessageCollection> readMessages = chatMessageRepository.findReadMessage(roomNumber, exitTime, pageable);
    //
    //            log.info(readMessages.toString());
    //            // MessageDto로 변환하여 반환
    //            return readMessages.stream()
    //                    .map(MessageDto::messageDtoFromEntity)
    //                    .toList();
    //        }
    //
    //        // Optional이 empty인 경우, 즉 사용자의 퇴장 기록이 없는 경우, 빈 리스트 반환
    //        return Collections.emptyList();
    @Override
    public List<TeamMessageDto> getReadTeamMessage(String uuid, String roomNumber, Pageable pageable) {
        Optional<TeamChatMessageCollection> optionalExitMessage = teamChatMessageRepository.findLatestExitByRoomNumber(roomNumber, uuid);

        return null;
    }

}















