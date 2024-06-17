package com.spacestar.back.teamChat.service;

import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.dto.TeamChatRoomDetailDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomListDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomRecruitDto;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.repository.TeamChatMemberJpaRepository;
import com.spacestar.back.teamChat.repository.TeamChatRoomJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeamChatRoomServiceImp implements TeamChatRoomService {

    private final TeamChatRoomJpaRepository teamChatRoomJpaRepository;
    private final TeamChatMemberJpaRepository teamChatMemberJpaRepository;
    private final TeamChatMemberService teamChatMemberService;

    @Override
    public void addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto) {
        String roomNumber = UUID.randomUUID().toString();
        TeamChatRoom teamChatRoom = TeamChatRoomReqDto.toEntity(teamChatRoomReqDto, roomNumber);
        // 채팅방 생성
        teamChatRoomJpaRepository.save(teamChatRoom);

        // 채팅방에 참여자 추가 (방장인 경우)
        teamChatMemberService.addMemberToTeamChatRoom(teamChatRoom, uuid, true);

    }

    @Override
    public List<TeamChatRoomListDto> getTeamChatRoomList(String uuid) {
        // uuid 를 통해 멤버 객체가져온다 => 멤버 객체를 통해 채팅방 리스트를 가져온다 => 채팅방 리스트를 dto 로 변환한다
        return teamChatMemberJpaRepository.findAllByMemberUuid(uuid).stream()
                .map(member -> new TeamChatRoomListDto(
                        member.getTeamChatRoom().getRoomNumber(),
                        member.getTeamChatRoom().getRoomName()))
                .toList();
    }

    @Override
    public List<TeamChatRoomRecruitDto> getTeamChatRoomRecruitList() {
        List<TeamChatRoom> teamChatRoomList = teamChatRoomJpaRepository.findAll();
        //팀챗룸을 가져오고 그에 해당하는 멤버들을 가져와서 dto 로 변환한다
        return teamChatRoomList.stream()
                .map(teamChatRoom -> {
                    List<TeamChatMember> teamChatMemberList = teamChatMemberJpaRepository.findByTeamChatRoomId(teamChatRoom.getId());
                    List<TeamChatRoomDetailDto> memberUuidList = teamChatMemberList.stream()
                            .map(TeamChatRoomDetailDto::fromEntity)
                            .collect(Collectors.toList());

                    return TeamChatRoomRecruitDto.fromEntities(teamChatRoom, memberUuidList);
                })
                .toList();
    }

}
