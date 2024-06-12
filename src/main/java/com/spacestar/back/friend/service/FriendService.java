package com.spacestar.back.friend.service;

import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.FriendListResDto;
import com.spacestar.back.friend.dto.res.FriendRequestResDto;

public interface FriendService {
    void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    FriendListResDto getFriendList(String uuid);

    FriendRequestResDto getFriendRequestList(String uuid);

    void acceptFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    void rejectFriend(String uuid, FriendUuidReqDto friendUuidReqDto);
}
