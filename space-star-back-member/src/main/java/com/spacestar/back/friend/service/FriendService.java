package com.spacestar.back.friend.service;

import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.*;
import com.spacestar.back.friend.vo.res.FriendSendResVo;

import java.util.List;

public interface FriendService {
    void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    List<FriendListResDto> getFriendList(String uuid, String type);

    List<FriendRequestResDto> getFriendRequestList(String uuid);

    void acceptFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    void rejectFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    FriendNowResDto isFriendRequest(String uuid, String targetUuid);


    List<FriendSendResDto> getFriendSendList(String uuid);
}
