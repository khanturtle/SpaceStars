package com.spacestar.back.teamChat.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.teamChat.domain.entity.TeamChatMember;
import com.spacestar.back.teamChat.domain.entity.TeamChatRoom;
import com.spacestar.back.teamChat.dto.*;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.enums.TeamParticipationType;
import com.spacestar.back.teamChat.repository.CustomTeamChatRepository;
import com.spacestar.back.teamChat.repository.TeamChatMemberJpaRepository;
import com.spacestar.back.teamChat.repository.TeamChatRoomJpaRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.spacestar.back.teamChat.enums.TeamParticipationType.BANNED;
import static com.spacestar.back.teamChat.enums.TeamParticipationType.JOINED;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeamChatRoomServiceImp implements TeamChatRoomService {

    private final TeamChatRoomJpaRepository teamChatRoomJpaRepository;
    private final TeamChatMemberJpaRepository teamChatMemberJpaRepository;
    private final TeamChatMemberService teamChatMemberService;
    private final CustomTeamChatRepository customTeamChatRepository;
    @Override
    public TeamChatRoomNumberDto addTeamChatRoom(String uuid, TeamChatRoomReqDto teamChatRoomReqDto) {



        String roomNumber = UUID.randomUUID().toString();
        TeamChatRoom teamChatRoom = TeamChatRoomReqDto.toEntity(teamChatRoomReqDto, roomNumber);
        // 채팅방 생성
        teamChatRoomJpaRepository.save(teamChatRoom);
        // 채팅방에 참여자 추가 (방장인 경우)
        teamChatMemberService.addMemberToTeamChatRoom(teamChatRoom, uuid, true);
        return TeamChatRoomNumberDto.builder().roomNumber(roomNumber).build();

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
    public List<TeamChatRoomRecruitDto> getTeamChatRoomRecruitList(Long gameId, Integer maxMembers, Boolean isFinished) {
        List<TeamChatRoom> teamChatRoomList = customTeamChatRepository.findQueryDslTeamChatRoom(gameId, maxMembers, isFinished);
        //팀챗룸을 가져오고 그에 해당하는 멤버들을 가져와서 dto 로 변환한다
        return teamChatRoomList.stream()
                .map(teamChatRoom -> {
                    List<TeamChatMember> teamChatMemberList = teamChatMemberJpaRepository.findByTeamChatRoomId(
                            teamChatRoom.getId());
                    List<TeamChatRoomDetailDto> memberUuidList = teamChatMemberList.stream()
                            .map(TeamChatRoomDetailDto::fromEntity)
                            .collect(Collectors.toList());

                    return TeamChatRoomRecruitDto.fromEntities(teamChatRoom, memberUuidList);
                })
                .toList();
    }

    @Override
    public TeamChatRoomDto getTeamChatRoomDetail(String roomNumber) {
        // 채팅방 번호로 채팅방을 가져온다
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);
        return TeamChatRoomDto.ToDto(teamChatRoom);
    }

    @Override
    public List<TeamChatRoomMemberDto> getTeamChatRoomMembers(String roomNumber) {

        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);
//        List<TeamChatMember> teamChatMemberList = teamChatMemberJpaRepository.findByTeamChatRoomId(teamChatRoom.getId());
        List<TeamChatMember> teamChatMemberList = teamChatMemberJpaRepository.findCurrentMembersInChatRoom(
                teamChatRoom.getId(), JOINED);

        return teamChatMemberList.stream()
                .map((TeamChatRoomMemberDto::toDto))
                .toList();
    }


    @Override
    public void joinTeamChatRoom(String uuid, String roomNumber, String password) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);
        Optional<TeamChatMember> optionalMember = teamChatMemberJpaRepository.findByTeamChatRoomAndMemberUuid(
                teamChatRoom, uuid);

        //teamChatRoom 의 멤버수 쿼리문
        int MemberCount = teamChatRoomJpaRepository.findCountMembersByTeamChatRoom(teamChatRoom);

        // 방 꽉찼음
        if (MemberCount == teamChatRoom.getMaxMembers()) {
            throw new GlobalException(ResponseStatus.FULL_MEMBER);
        }

        // 비밀번호 틀렸음
        if (teamChatRoom.getIsPassword() && !teamChatRoom.getPassword().equals(password)) {
            throw new GlobalException(ResponseStatus.WRONG_PASSWORD);
        }

        teamChatMemberService.addMemberToTeamChatRoom(teamChatRoom, uuid, false);



    }

    @Override
    public void exitTeamChatRoom(String uuid, String roomNumber) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);
        teamChatMemberService.deleteMemberToTeamChatRoom(teamChatRoom, uuid);

    }

    @Override
    public void kickTeamChatRoom(String uuid, String roomNumber, String receiverUuid) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);

        TeamChatMember teamChatMemberOwner = teamChatMemberJpaRepository.findByMemberUuidAndId(uuid,
                teamChatRoom.getId());
        TeamChatMember teamChatMemberReceiver = teamChatMemberJpaRepository.findByMemberUuidAndId(receiverUuid,
                teamChatRoom.getId());

        if (!teamChatMemberOwner.getOwnerStatus()) {
            throw new GlobalException(ResponseStatus.NOT_OWNER);
        }

        TeamChatMemberDto teamChatMemberDto = TeamChatMemberDto.toDto(teamChatMemberReceiver);
        teamChatMemberDto.setTeamParticipationType(BANNED);
        teamChatMemberJpaRepository.save(TeamChatMemberDto.toEntity(teamChatMemberDto, teamChatMemberReceiver.getId()));


    }

    @Override
    public void changeOwnerTeamChatRoom(String uuid, String roomNumber, String receiverUuid) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);
        log.info("teamChatRoom : {}", teamChatRoom.getId());
        TeamChatMember teamChatMemberOwner = teamChatMemberJpaRepository.findByMemberUuidAndId(uuid,
                teamChatRoom.getId());
        TeamChatMember teamChatMemberReceiver = teamChatMemberJpaRepository.findByMemberUuidAndId(receiverUuid,
                teamChatRoom.getId());
        log.info("owner : {}", teamChatMemberOwner.toString());
        log.info("receiver : {}", teamChatMemberReceiver.toString());
        if (!teamChatMemberOwner.getOwnerStatus()) {
            throw new GlobalException(ResponseStatus.NOT_OWNER);
        }

        TeamChatMemberDto teamChatMemberDtoOwner = TeamChatMemberDto.toDto(teamChatMemberOwner);
        teamChatMemberDtoOwner.setOwnerStatus(false);
        teamChatMemberJpaRepository.save(
                TeamChatMemberDto.toEntity(teamChatMemberDtoOwner, teamChatMemberOwner.getId()));

        TeamChatMemberDto teamChatMemberDtoReceiver = TeamChatMemberDto.toDto(teamChatMemberReceiver);
        teamChatMemberDtoReceiver.setOwnerStatus(true);
        teamChatMemberJpaRepository.save(
                TeamChatMemberDto.toEntity(teamChatMemberDtoReceiver, teamChatMemberReceiver.getId()));

    }

    @Override
    public void finishRecruit(String uuid, String roomNumber) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);

        TeamChatMember teamChatMemberOwner = teamChatMemberJpaRepository.findByMemberUuidAndId(uuid,
                teamChatRoom.getId());
        if (!teamChatMemberOwner.getOwnerStatus()) {
            throw new GlobalException(ResponseStatus.NOT_OWNER);
        }

        TeamChatRoomDto teamChatRoomDto = TeamChatRoomDto.ToDto(teamChatRoom);
        teamChatRoomDto.setIsFinished(true);
        TeamChatRoom teamChatRoomEntity = TeamChatRoomDto.ToEntity(teamChatRoomDto, teamChatRoom.getId());
        teamChatRoomJpaRepository.save(teamChatRoomEntity);

    }

    @Override
    public void changeTeamChatRoom(String uuid, String roomNumber, TeamChatRoomReqDto teamChatRoomReqDto) {
        TeamChatRoom teamChatRoom = teamChatRoomJpaRepository.findByRoomNumber(roomNumber);

        TeamChatMember teamChatMemberOwner = teamChatMemberJpaRepository.findByMemberUuidAndId(uuid,
                teamChatRoom.getId());
        if (!teamChatMemberOwner.getOwnerStatus()) {
            throw new GlobalException(ResponseStatus.NOT_OWNER);
        }

        TeamChatRoomDto teamChatRoomDto = TeamChatRoomDto.builder()
                .gameId(teamChatRoomReqDto.getGameId())
                .roomNumber(roomNumber)
                .roomName(teamChatRoomReqDto.getRoomName())
                .maxMembers(teamChatRoomReqDto.getMaxMembers())
                .isPassword(teamChatRoomReqDto.getIsPassword())
                .password(teamChatRoomReqDto.getPassword())
                .isFinished(teamChatRoom.getIsFinished())
                .memo(teamChatRoomReqDto.getMemo())
                .build();
        TeamChatRoom teamChatRoomEntity = TeamChatRoomDto.ToEntity(teamChatRoomDto, teamChatRoom.getId());
        teamChatRoomJpaRepository.save(teamChatRoomEntity);
    }

    @Override
    public boolean isMember(String uuid, String roomNumber) {
        return teamChatRoomJpaRepository.existsByRoomNumberAndMemberUuid(roomNumber, uuid);
    }


}
