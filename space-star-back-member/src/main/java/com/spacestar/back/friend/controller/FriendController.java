package com.spacestar.back.friend.controller;

import com.spacestar.back.friend.service.FriendService;
import com.spacestar.back.friend.vo.req.FriendUuidReqVo;
import com.spacestar.back.friend.vo.res.FriendListResVo;
import com.spacestar.back.friend.vo.res.FriendNowResVo;
import com.spacestar.back.friend.vo.res.FriendRequestResVo;
import com.spacestar.back.friend.vo.res.FriendSendResVo;
import com.spacestar.back.global.ResponseEntity;
import com.spacestar.back.global.ResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import com.spacestar.back.friend.dto.req.FriendUuidReqDto;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Friend", description = "친구")
@RequiredArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService friendService;
    private final ModelMapper mapper;

    @Operation(summary = "친구 목록 조회")
    @GetMapping("/list")
    public ResponseEntity<List<FriendListResVo>> getFriendList(
            @RequestHeader("UUID") String uuid,
            @RequestParam(value = "type", required = false, defaultValue = "all") String type )
    {

        log.info("uuid : {}", uuid);
        log.info("type : {}", type);
        return new ResponseEntity<>(ResponseSuccess.FRIEND_LIST_SELECT_SUCCESS,
                friendService.getFriendList(uuid, type).stream()
                        .map(friendListResDto -> mapper.map(friendListResDto, FriendListResVo.class))
                        .toList());
    }

    @Operation(summary = "친구 신청")
    @PostMapping("/request")
    public ResponseEntity<Void> addFriend(@RequestHeader("UUID") String uuid,
                                          @RequestBody FriendUuidReqVo friendUuidReqVo) {

        friendService.addFriend(uuid, mapper.map(friendUuidReqVo, FriendUuidReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.FRIEND_ADD_SUCCESS);

    }

    @Operation(summary = "내가 받은 친구 요청 목록")
    @GetMapping("/request")
    public ResponseEntity<List<FriendRequestResVo>> getFriendRequestList(@RequestHeader("UUID") String uuid) {


        return new ResponseEntity<>(ResponseSuccess.FRIEND_REQUEST_SELECT_SUCCESS,
                friendService.getFriendRequestList(uuid).stream()
                        .map(friendRequestResDto -> mapper.map(friendRequestResDto, FriendRequestResVo.class))
                        .toList());
    }

    @Operation(summary = "친구 수락")
    @PatchMapping("/request")
    public ResponseEntity<Void> acceptFriend(@RequestHeader("UUID") String uuid,
                                             @RequestBody FriendUuidReqVo friendUuidReqVo) {

        friendService.acceptFriend(uuid, mapper.map(friendUuidReqVo, FriendUuidReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.FRIEND_ACCEPT_SUCCESS);
    }

    @Operation(summary = "친구 요청 거절")
    @DeleteMapping("/request")
    public ResponseEntity<Void> rejectFriend(@RequestHeader("UUID") String uuid,
                                             @RequestBody FriendUuidReqVo friendUuidReqVo) {

        friendService.rejectFriend(uuid, mapper.map(friendUuidReqVo, FriendUuidReqDto.class));

        return new ResponseEntity<>(ResponseSuccess.FRIEND_REJECT_SUCCESS);
    }

    @Operation(summary = "친구 삭제")
    @DeleteMapping
    public ResponseEntity<Void> deleteFriend(@RequestHeader("UUID") String uuid,
                                             @RequestBody FriendUuidReqVo friendUuidReqVo) {
        friendService.rejectFriend(uuid, mapper.map(friendUuidReqVo, FriendUuidReqDto.class));
        return new ResponseEntity<>(ResponseSuccess.FRIEND_DELETE_SUCCESS);
    }

    @Operation(summary = "친구 회원 상호 상태 확인")
    @GetMapping("/is-friend/request/{targetUuid}")
    public ResponseEntity<FriendNowResVo> isFriendRequest(@RequestHeader("UUID") String uuid,
                                                          @PathVariable String targetUuid) {

        return new ResponseEntity<>(ResponseSuccess.FRIEND_NOW_SELECT_SUCCESS,
                mapper.map(friendService.isFriendRequest(uuid, targetUuid), FriendNowResVo.class));

    }

    @Operation(summary = "내가 보낸 친구 요청 목록")
    @GetMapping("/request/send")
    public ResponseEntity<List<FriendSendResVo>> getFriendSendList(@RequestHeader("UUID") String uuid) {

        return new ResponseEntity<>(ResponseSuccess.FRIEND_SEND_SELECT_SUCCESS,
                friendService.getFriendSendList(uuid).stream()
                        .map(friendSendResDto -> mapper.map(friendSendResDto, FriendSendResVo.class))
                        .toList());

    }
}
