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
        List<TeamChatMemberDto> teamChatMemberDtoList = teamChatMemberList.stream()
                .map(TeamChatMemberDto::toDto)
                .toList();
            for (TeamChatMemberDto teamChatMemberDto : teamChatMemberDtoList) {
            if (teamChatMemberDto.getMemberUuid().equals(uuid)) {
                teamChatMemberDto.setTeamParticipationType(LEFT);
                teamChatMemberJpaRepository.save(TeamChatMemberDto.toEntity(teamChatMemberDto));
                if (teamChatMemberDto.getOwnerStatus()) {
                    // 다음으로 들어온 멤버에게 방장 권한 부여
                    List<TeamChatMemberDto> activeMembers = teamChatMemberDtoList.stream()
                            .filter(m -> m.getTeamParticipationType() == TeamParticipationType.JOINED) // LEFT 상태가 아닌 멤버들만 필터링
                            .toList();
                    if (!activeMembers.isEmpty()) {
                        TeamChatMemberDto nextOwner = activeMembers.get(0); // 첫 번째로 들어온 멤버를 방장으로 지정
                        nextOwner.setOwnerStatus(true);
                        teamChatMemberJpaRepository.save(TeamChatMemberDto.toEntity(nextOwner));
                    }
                }

            }
        }
    }



}
