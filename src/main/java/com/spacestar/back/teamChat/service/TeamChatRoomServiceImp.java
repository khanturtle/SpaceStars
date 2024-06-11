package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.repository.TeamChatRoomJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeamChatRoomServiceImp implements TeamChatRoomService{

    private final TeamChatRoomJpaRepository teamChatRoomJpaRepository;
    private final TeamChatMemberService teamChatMemberService;
    @Override
    public void addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto) {
        String roomNumber = UUID.randomUUID().toString();
        TeamChatRoom teamChatRoom = TeamChatRoomReqDto.toEntity(teamChatRoomReqDto, roomNumber);
        // 채팅방 생성
        teamChatRoomJpaRepository.save(teamChatRoom);

        // 채팅방에 참여자 추가 (방장인 경우)
        teamChatMemberService.addMemberToTeamChatRoom(teamChatRoom, uuid,true);

    }
}
