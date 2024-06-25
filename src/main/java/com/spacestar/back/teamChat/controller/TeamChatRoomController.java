package com.spacestar.back.teamChat.controller;

import com.spacestar.back.chat.dto.ChatRoomNumberDto;
import com.spacestar.back.converter.ConvertToIndexVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import com.spacestar.back.teamChat.dto.TeamChatRoomDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomListDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomMemberDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomNumberDto;
import com.spacestar.back.teamChat.dto.TeamChatRoomRecruitDto;
import com.spacestar.back.teamChat.dto.req.TeamChatRoomReqDto;
import com.spacestar.back.teamChat.service.TeamChatRoomService;
import com.spacestar.back.teamChat.vo.req.TeamChatJoinReqVo;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomJoinReqVo;
import com.spacestar.back.teamChat.vo.req.TeamChatRoomReqVo;
import com.spacestar.back.teamChat.vo.res.TeamChatRoomDetailResVo;
import com.spacestar.back.teamChat.vo.res.TeamChatRoomMemberResVo;
import com.spacestar.back.teamChat.vo.res.TeamChatRoomNumberResVo;
import com.spacestar.back.teamChat.vo.res.TeamChatRoomRecruitReqVo;
import com.spacestar.back.teamChat.vo.res.TeamChatRoomResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/team")
@Tag(name = "TeamChatRoom", description = "팀 채팅방")
public class TeamChatRoomController {
    private final TeamChatRoomService teamChatRoomService;
    private final ModelMapper mapper;

    // 팀 채팅방 생성
    @Operation(summary = "팀 채팅방 생성", description = "팀 채팅방을 생성합니다.")
    @PostMapping("/chatroom/create")
    public ResponseEntity<TeamChatRoomNumberResVo> addTeamChatRoom(@RequestHeader String uuid,
                                                @Valid @RequestBody TeamChatRoomReqVo teamChatRoomReqVo) {
        TeamChatRoomReqDto teamChatRoomReqDto = mapper.map(teamChatRoomReqVo, TeamChatRoomReqDto.class);
        TeamChatRoomNumberDto teamChatRoomNumberDto = teamChatRoomService.addTeamChatRoom(uuid,teamChatRoomReqDto);
        TeamChatRoomNumberResVo teamChatRoomNumberResVo = mapper.map(teamChatRoomNumberDto, TeamChatRoomNumberResVo.class);
        return new ResponseEntity<>(ResponseSuccess.CREATE_TEAM_CHATROOM_SUCCESS,teamChatRoomNumberResVo);
    }

    // 팀 채팅방 목록 조회
    @Operation(summary = "내가 속한 팀 채팅방 리스트 조회", description = "내가 속한 팀 채팅방 리스트 조회")
    @GetMapping("/chatroom/list")
    public ResponseEntity<List<TeamChatRoomResVo>> getTeamChatRoomList(@RequestHeader String uuid) {
        // 팀 채팅방 목록 조회 Dto 로 받아오기
        List<TeamChatRoomListDto> teamChatRoomList = teamChatRoomService.getTeamChatRoomList(uuid);
        List<TeamChatRoomResVo> teamChatRoomResVos = ConvertToIndexVo.convertAndIndex(teamChatRoomList, TeamChatRoomResVo.class, mapper);
        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_LIST_SUCCESS, teamChatRoomResVos);
    }

    // 게임, 모집인원수, 모집완료
    @Operation(summary = "팀원 모집 방 목록 조회", description = "팀원 모집 방 목록을 조회합니다.")
    @GetMapping("/chatroom/recruit/list")
    public ResponseEntity<List<TeamChatRoomRecruitReqVo>> getTeamChatRoomRecruitList(@RequestParam(value = "game",required = false ) Long gameId,
                                                                                     @RequestParam(value = "maxMembers",required = false) Integer maxMembers,
                                                                                     @RequestParam(value = "isFinished", required = false) Boolean isFinished){

        List<TeamChatRoomRecruitDto> teamChatRoomRecruitDtos = teamChatRoomService.getTeamChatRoomRecruitList(gameId, maxMembers, isFinished);

        //convertor 사용
        List<TeamChatRoomRecruitReqVo> teamChatRoomRecruitReqVos =
                ConvertToIndexVo.convertAndIndex(teamChatRoomRecruitDtos, TeamChatRoomRecruitReqVo.class, mapper);

        return new ResponseEntity<>(ResponseSuccess.GET_CHATROOM_RECRUIT_LIST_SUCCESS, teamChatRoomRecruitReqVos);
    }

    @Operation(summary = "팀 채팅방 상세 조회", description = "팀 채팅방 상세 정보를 조회합니다.")
    @GetMapping("/chatroom/{roomNumber}")
    public ResponseEntity<TeamChatRoomDetailResVo> getTeamChatRoomDetail(@PathVariable String roomNumber) {
        TeamChatRoomDto teamChatRoomDto = teamChatRoomService.getTeamChatRoomDetail(roomNumber);
        TeamChatRoomDetailResVo teamChatRoomDetailResVo = mapper.map(teamChatRoomDto, TeamChatRoomDetailResVo.class);
        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_DETAIL_SUCCESS,teamChatRoomDetailResVo);
    }

    @Operation(summary = "팀 채팅방에 가입된 유저",description = "팀 채팅방에 가입된 유저를 조회합니다.")
    @GetMapping("/chatroom/{roomNumber}/members")
    public ResponseEntity<List<TeamChatRoomMemberResVo>> getTeamChatRoomMembers(@PathVariable String roomNumber){
        List<TeamChatRoomMemberDto> teamChatRoomMemberDtos = teamChatRoomService.getTeamChatRoomMembers(roomNumber);
        List<TeamChatRoomMemberResVo> teamChatRoomMemberResVoList = ConvertToIndexVo.convertAndIndex(teamChatRoomMemberDtos, TeamChatRoomMemberResVo.class, mapper);
        return new ResponseEntity<>(ResponseSuccess.GET_TEAM_CHATROOM_MEMBERS_SUCCESS,teamChatRoomMemberResVoList);
    }


    @Operation(summary = "팀 채팅방 참가하기", description = "팀 채팅방을 참가합니다.")
    @PostMapping("/chatroom/join/{roomNumber}")
    public ResponseEntity<TeamChatRoomNumberResVo> joinTeamChatRoom(@RequestHeader String uuid,
                                              @PathVariable String roomNumber,@RequestBody @Valid TeamChatRoomJoinReqVo teamChatRoomJoinReqVo){
        String Password = teamChatRoomJoinReqVo.getPassword();
        teamChatRoomService.joinTeamChatRoom(uuid, roomNumber, Password);
        ChatRoomNumberDto chatRoomNumberDto = ChatRoomNumberDto.builder()
                .roomNumber(roomNumber)
                .build();
        TeamChatRoomNumberResVo teamChatRoomNumberResVo = mapper.map(chatRoomNumberDto, TeamChatRoomNumberResVo.class);
        return new ResponseEntity<>(ResponseSuccess.JOIN_TEAM_CHATROOM_SUCCESS,teamChatRoomNumberResVo);
    }

    @Operation(summary = "팀 채팅방 나가기", description = "팀 채팅방을 나갑니다.")
    @PatchMapping("/chatroom/exit/{roomNumber}")
    public ResponseEntity<TeamChatRoomNumberResVo> exitTeamChatRoom(@RequestHeader String uuid,
                                              @PathVariable String roomNumber){
        teamChatRoomService.exitTeamChatRoom(uuid, roomNumber);
        ChatRoomNumberDto chatRoomNumberDto = ChatRoomNumberDto.builder()
                .roomNumber(roomNumber)
                .build();
        TeamChatRoomNumberResVo teamChatRoomNumberResVo = mapper.map(chatRoomNumberDto, TeamChatRoomNumberResVo.class);
        return new ResponseEntity<>(ResponseSuccess.EXIT_TEAM_CHATROOM_SUCCESS,teamChatRoomNumberResVo);
    }

    //강퇴하기
    @Operation(summary = "팀 채팅방 강퇴하기", description = "팀 채팅방에서 특정 유저를 강퇴합니다.")
    @PatchMapping("/chatroom/kick/{roomNumber}")
    public ResponseEntity<Void> kickTeamChatRoom(@RequestHeader String uuid,
                                              @PathVariable String roomNumber,@Valid @RequestBody TeamChatJoinReqVo teamChatJoinReqVo){
        String receiverUuid = teamChatJoinReqVo.getSenderUuid();
        teamChatRoomService.kickTeamChatRoom(uuid, roomNumber, receiverUuid);

        return new ResponseEntity<>(ResponseSuccess.KICK_TEAM_CHATROOM_SUCCESS);
    }
    //방장 변경하기
    @Operation(summary = "팀 채팅방 방장 변경하기", description = "팀 채팅방의 방장을 변경합니다.")
    @PatchMapping("/chatroom/owner/{roomNumber}")
    public ResponseEntity<Void> changeOwnerTeamChatRoom(@RequestHeader String uuid,
                                                    @PathVariable String roomNumber,@Valid @RequestBody TeamChatJoinReqVo teamChatJoinReqVo){
        String receiverUuid = teamChatJoinReqVo.getSenderUuid();
        teamChatRoomService.changeOwnerTeamChatRoom(uuid, roomNumber, receiverUuid);

        return new ResponseEntity<>(ResponseSuccess.CHANGE_OWNER_TEAM_CHATROOM_SUCCESS);
    }

    //모집 완료
    @Operation(summary = "팀원 모집 완료", description = "팀원 모집을 완료합니다.")
    @PatchMapping("/chatroom/recruit/{roomNumber}")
    public ResponseEntity<Void> finishRecruit(@RequestHeader String uuid,
                                           @PathVariable String roomNumber){
        teamChatRoomService.finishRecruit(uuid, roomNumber);

        return new ResponseEntity<>(ResponseSuccess.FINISH_RECRUIT_SUCCESS);
    }
    //방정보 변경하기
    @Operation(summary = "팀 채팅방 정보 변경하기", description = "팀 채팅방의 정보를 변경합니다.")
    @PutMapping("/chatroom/{roomNumber}")
    public ResponseEntity<Void> changeTeamChatRoom(@RequestHeader String uuid,
                                                @PathVariable String roomNumber,@Valid @RequestBody TeamChatRoomReqVo teamChatRoomReqVo){
        TeamChatRoomReqDto teamChatRoomReqDto = mapper.map(teamChatRoomReqVo, TeamChatRoomReqDto.class);
        teamChatRoomService.changeTeamChatRoom(uuid, roomNumber, teamChatRoomReqDto);


        return new ResponseEntity<>(ResponseSuccess.CHANGE_TEAM_CHATROOM_SUCCESS);
    }
}
