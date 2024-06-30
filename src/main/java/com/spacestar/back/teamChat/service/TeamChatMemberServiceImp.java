package com.spacestar.back.teamChat.service;

import static com.spacestar.back.teamChat.enums.TeamParticipationType.LEFT;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.dto.TeamChatMemberDto;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
import com.spacestar.back.teamChat.repository.TeamChatMemberJpaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
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

    //나가는거 처리
    @Override
    public void deleteMemberToTeamChatRoom(TeamChatRoom teamChatRoom, String uuid) {
        List<TeamChatMember> teamChatMemberList = teamChatMemberJpaRepository.findByTeamChatRoomId(teamChatRoom.getId());
        TeamChatMember leavingMember = null;

        for (TeamChatMember teamChatMember : teamChatMemberList) {
            if (teamChatMember.getMemberUuid().equals(uuid)) {
                leavingMember = teamChatMember;
                break;
            }
        }

        if (leavingMember != null) {
            Boolean leavingMemberOwnerStatus = leavingMember.getOwnerStatus();
            TeamChatMemberDto leavingMemberDto = TeamChatMemberDto.toDto(leavingMember);
            leavingMemberDto.setTeamParticipationType(TeamParticipationType.LEFT);
            leavingMemberDto.setOwnerStatus(false);
            teamChatMemberJpaRepository.save(TeamChatMemberDto.toEntity(leavingMemberDto, leavingMember.getId()));
            log.info("leavingMember : {}", leavingMember.toString());
            if (leavingMemberOwnerStatus) {
                log.info("여기 발동되나");
                List<TeamChatMember> activeMembers = teamChatMemberJpaRepository.findJoinedMembersByTeamChatRoomId(teamChatRoom.getId());

                if (!activeMembers.isEmpty()) {
                    log.info("activeMembers : {}", activeMembers);
                    TeamChatMember nextOwner = activeMembers.get(0);
                    TeamChatMemberDto nextOwnerDto = TeamChatMemberDto.toDto(nextOwner);
                    nextOwnerDto.setOwnerStatus(true);
                    teamChatMemberJpaRepository.save(TeamChatMemberDto.toEntity(nextOwnerDto, nextOwner.getId()));
                }
            }
        }
    }
}


