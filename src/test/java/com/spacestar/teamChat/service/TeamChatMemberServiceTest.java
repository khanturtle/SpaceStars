package com.spacestar.teamChat.service;

import static org.mockito.Mockito.*;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
import com.spacestar.back.teamChat.repository.TeamChatMemberJpaRepository;
import com.spacestar.back.teamChat.service.TeamChatMemberService;
import com.spacestar.back.teamChat.service.TeamChatMemberServiceImp;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TeamChatMemberServiceTest {

    @Mock
    private TeamChatMemberJpaRepository teamChatMemberJpaRepository;

    private TeamChatMemberService teamChatMemberService;

    private TeamChatRoom teamChatRoom;
    private String uuid;
    private Boolean ownerStatus;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        teamChatMemberService = new TeamChatMemberServiceImp(teamChatMemberJpaRepository); // 실제 구현체 생성 및 주입

        teamChatRoom = TeamChatRoom.builder()
                .id(1L)
                .build();
        uuid = "test-uuid";
        ownerStatus = false;
    }

    @Test
    public void testAddMemberToTeamChatRoom_MemberLeft_ReturnsJoined() {
        // 테스트 시나리오 설정: 방을 나간 상태인 사용자가 다시 방에 참여하는 경우
        TeamChatMember leftMember = TeamChatMember.builder()
                .id(1L)
                .teamChatRoom(teamChatRoom)
                .memberUuid(uuid)
                .ownerStatus(ownerStatus)
                .teamParticipationType(TeamParticipationType.LEFT)
                .build();

        // teamChatMemberJpaRepository.findByTeamChatRoomAndMemberUuid() 메서드가
        // leftMember를 반환하도록 설정
        when(teamChatMemberJpaRepository.findByTeamChatRoomAndMemberUuid(teamChatRoom, uuid))
                .thenReturn(Optional.of(leftMember));

        // addMemberToTeamChatRoom() 메서드를 호출하여 멤버를 추가 또는 상태 업데이트
        teamChatMemberService.addMemberToTeamChatRoom(teamChatRoom, uuid, ownerStatus);

        // teamChatMemberJpaRepository.save() 메서드가 한 번 호출되었고,
        // 해당 멤버의 상태가 JOINED로 업데이트되었는지 확인
        verify(teamChatMemberJpaRepository, times(1)).save(argThat(argument ->
                argument.getTeamParticipationType() == TeamParticipationType.JOINED &&
                        argument.getId().equals(leftMember.getId())
        ));
    }
}
