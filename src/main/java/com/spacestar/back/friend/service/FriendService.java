package com.spacestar.back.friend.service;

import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.FriendListResDto;
import com.spacestar.back.friend.dto.res.FriendNowResDto;
import com.spacestar.back.friend.dto.res.FriendRequestResDto;
<<<<<<< HEAD
import com.spacestar.back.friend.vo.res.FriendNowResVo;
=======
import com.spacestar.back.friend.dto.res.IsFriendResDto;
>>>>>>> a15bd3f460bdbbd3084933c92baefa50dbd98ec2

public interface FriendService {
    void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    FriendListResDto getFriendList(String uuid);

    FriendRequestResDto getFriendRequestList(String uuid);

    void acceptFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

    void rejectFriend(String uuid, FriendUuidReqDto friendUuidReqDto);

<<<<<<< HEAD
    FriendNowResDto isFriendRequest(String uuid, String targetUuid);
=======
    IsFriendResDto isFriend(String uuid, String targetUuid);
>>>>>>> a15bd3f460bdbbd3084933c92baefa50dbd98ec2
}
