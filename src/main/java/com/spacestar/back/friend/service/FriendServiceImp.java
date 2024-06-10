package com.spacestar.back.friend.service;

import com.spacestar.back.friend.domain.Friend;
import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.FriendListResDto;
import com.spacestar.back.friend.dto.res.FriendRequestResDto;
import com.spacestar.back.friend.enums.FriendType;
import com.spacestar.back.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendServiceImp implements FriendService{

    private final FriendRepository friendRepository;

    //친구 신청
    @Override
    public void addFriend(String uuid, FriendUuidReqDto friendUuidReqDto) {

        //내가 친구 신청
        Friend me = Friend.builder()
                .friendType(FriendType.SENDER)
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

    //친구 목록 조회
    @Override
    public FriendListResDto getFriendList(String uuid) {

        List<Friend> friendList = friendRepository.findByUuid(uuid);
        List<String> friendUuidList = new ArrayList<>();

        for (Friend friend : friendList){

            //친구일 경우에만
            if (friend.getFriendType() == FriendType.FRIEND)
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

        for (Friend friend : friendList){
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
                .friendType(FriendType.FRIEND)
                .friendUuid(friend.getFriendUuid())
                .uuid(friend.getUuid())
                .build();

        friendRepository.save(realFriend);

        //상대방
        Friend friend2 = friendRepository.findByFriendUuidAndUuid(uuid, friendUuidReqDto.getFriendUuid());

        Friend realFriend2 = Friend.builder()
                .id(friend2.getId())
                .friendType(FriendType.FRIEND)
                .friendUuid(friend2.getFriendUuid())
                .uuid(friend2.getUuid())
                .build();

        friendRepository.save(realFriend2);
    }
}







