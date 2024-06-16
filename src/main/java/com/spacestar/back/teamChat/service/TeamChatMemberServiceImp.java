package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.dto.TeamChatMemberDto;
import com.spacestar.back.teamChat.repository.TeamChatMemberJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeamChatMemberServiceImp implements TeamChatMemberService{
    private final TeamChatMemberJpaRepository teamChatMemberJpaRepository;
    @Override
    public void addMemberToTeamChatRoom(TeamChatRoom teamChatRoom, String uuid, Boolean ownerStatus) {
        TeamChatMember teamChatMember = TeamChatMemberDto.toEntity(teamChatRoom, uuid,ownerStatus);
        teamChatMemberJpaRepository.save(teamChatMember);
    }
}
