package com.spacestar.back.friend.service;

import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.FriendListResDto;
import com.spacestar.back.friend.dto.res.FriendNowResDto;
import com.spacestar.back.friend.dto.res.FriendRequestResDto;
import com.spacestar.back.friend.vo.res.FriendNowResVo;

public interface FriendService {
    void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    FriendListResDto getFriendList(String uuid);

    FriendRequestResDto getFriendRequestList(String uuid);

    void acceptFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    void rejectFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    FriendNowResDto isFriendRequest(String uuid, String targetUuid);
}
