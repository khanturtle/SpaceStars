package com.spacestar.back.friend.service;

import com.spacestar.back.friend.domain.Friend;
import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.FriendListResDto;
import com.spacestar.back.friend.dto.res.FriendNowResDto;
import com.spacestar.back.friend.dto.res.FriendRequestResDto;
import com.spacestar.back.friend.dto.res.IsFriendResDto;
import com.spacestar.back.friend.enums.FriendType;
import com.spacestar.back.friend.repository.FriendRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.spacestar.back.friend.enums.FriendType.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendServiceImp implements FriendService {

    private final FriendRepository friendRepository;

    //친구 신청
    @Override
    public void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto) {

        if (friendRepository.findByFriendUuidAndUuid(friendUuidReqDto.getFriendUuid(), uuid) != null) {
            throw new GlobalException(ResponseStatus.ALREADY_EXIST_FRIEND_REQUEST);
        } else {
            //내가 친구 신청
            Friend me = Friend.builder()
                    .friendType(SENDER)
                    .uuid(uuid)
                    .friendUuid(friendUuidReqDto.getFriendUuid())
                    .build();

            friendRepository.save(me);

            //상대 객체도 만들기
            Friend friend = Friend.builder()
                    .friendType(FriendType.RECEIVER)
                    .uuid(friendUuidReqDto.getFriendUuid())
                    .friendUuid(uuid)
                    .build();

            friendRepository.save(friend);
        }
    }

    //친구 목록 조회
    @Override
    public FriendListResDto getFriendList(String uuid) {

        List<Friend> friendList = friendRepository.findByUuid(uuid);
        List<String> friendUuidList = new ArrayList<>();

        for (Friend friend : friendList) {

            //친구일 경우에만
            if (friend.getFriendType() == FRIEND)
                friendUuidList.add(friend.getFriendUuid());
        }

        return FriendListResDto.builder()
                .friendUuidList(friendUuidList)
                .build();
    }

    @Override
    public FriendRequestResDto getFriendRequestList(String uuid) {

        List<Friend> friendList = friendRepository.findByUuid(uuid);
        List<String> friendRequestUuidList = new ArrayList<>();

        for (Friend friend : friendList) {
            //내가 받은 요청만
            if (friend.getFriendType() == FriendType.RECEIVER)
                friendRequestUuidList.add(friend.getFriendUuid());
        }

        return FriendRequestResDto.builder()
                .friendRequestUuidList(friendRequestUuidList)
                .build();
    }

    @Override
    public void acceptFriend(String uuid, FriendUuidReqDto friendUuidReqDto) {

        Friend friend = friendRepository.findByFriendUuidAndUuid(friendUuidReqDto.getFriendUuid(), uuid);

        //친구로 상태 변화
        Friend realFriend = Friend.builder()
                .id(friend.getId())
                .friendType(FRIEND)
                .friendUuid(friend.getFriendUuid())
                .uuid(friend.getUuid())
                .build();

        friendRepository.save(realFriend);

        //상대방
        Friend friend2 = friendRepository.findByFriendUuidAndUuid(uuid, friendUuidReqDto.getFriendUuid());

        Friend realFriend2 = Friend.builder()
                .id(friend2.getId())
                .friendType(FRIEND)
                .friendUuid(friend2.getFriendUuid())
                .uuid(friend2.getUuid())
                .build();

        friendRepository.save(realFriend2);
    }

    @Override
    public void rejectFriend(String uuid, FriendUuidReqDto friendUuidReqDto) {

        Friend friend = friendRepository.findByFriendUuidAndUuid(friendUuidReqDto.getFriendUuid(), uuid);
        friendRepository.delete(friend);

        Friend friend2 = friendRepository.findByFriendUuidAndUuid(uuid, friendUuidReqDto.getFriendUuid());
        friendRepository.delete(friend2);
    }

    //친구 상태 확인
    @Override
    public FriendNowResDto isFriendRequest(String uuid, String targetUuid) {

        Friend friend = friendRepository.findByFriendUuidAndUuid(targetUuid, uuid);

        if (friend == null) {
            return FriendNowResDto.builder()
                    .friendType(NONE)
                    .build();
        }

        else if (friend.getFriendType() == FRIEND) {
            return FriendNowResDto.builder()
                    .friendType(FriendType.FRIEND)
                    .build();

        } else if (friend.getFriendType() == SENDER) {
            return FriendNowResDto.builder()
                    .friendType(FriendType.SENDER)
                    .build();

        }
        return FriendNowResDto.builder()
                .friendType(FriendType.RECEIVER)
                .build();
    }

    @Override
    public IsFriendResDto isFriend(String uuid, String targetUuid) {

        Optional<Friend> friend = friendRepository.findByUuidAndFriendUuid(targetUuid, uuid);

        if (friend.isPresent()) {
            return IsFriendResDto.builder()
                    .isFriend(true)
                    .build();
        } else {
            return IsFriendResDto.builder()
                    .isFriend(false)
                    .build();
        }


    }
}







