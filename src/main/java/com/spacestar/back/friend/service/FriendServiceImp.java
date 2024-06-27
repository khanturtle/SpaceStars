package com.spacestar.back.friend.service;

import com.spacestar.back.friend.domain.Friend;
import com.spacestar.back.friend.dto.req.FriendUuidReqDto;
import com.spacestar.back.friend.dto.res.*;
import com.spacestar.back.friend.enums.FriendType;
import com.spacestar.back.friend.repository.FriendRepository;
import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.kafka.FriendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static com.spacestar.back.friend.enums.FriendType.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendServiceImp implements FriendService {

    private final FriendRepository friendRepository;

    private final KafkaTemplate<String, FriendMessage> kafkaTemplate;
    private static final String TOPIC = "dev.profile-service.friend-request";


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

            //친구 신청 받은 사람에게 알림
            FriendMessage friendMessage = FriendMessage.builder()
                    .senderUuid(uuid)
                    .receiverUuid(friendUuidReqDto.getFriendUuid())
                    .content("친구 신청이 왔습니다.")
                    .build();
            kafkaTemplate.send(TOPIC, friendMessage);
        }
    }

    //친구 목록 조회
    @Override
    public List<FriendListResDto> getFriendList(String uuid, String type) {

        log.info("uuid : " + uuid + " type : " + type);
        List<Friend> getFriendList = friendRepository.findByUuidAndFriendType(
                uuid, type.equals("all") ? FRIEND : type.equals("sender") ? SENDER : type.equals("receiver") ? RECEIVER : BLOCK
        );
        return IntStream.range(0, getFriendList.size())
                .mapToObj(i -> FriendListResDto.toDto(i, getFriendList.get(i).getFriendUuid()))
                .toList();
    }

    //친구 요청 목록 조회
    @Override
    public List<FriendRequestResDto> getFriendRequestList(String uuid) {

        List<Friend> friendList = friendRepository.findByUuid(uuid);

        List<String> friendRequestList = friendList.stream()
                .filter(friend -> friend.getFriendType() == RECEIVER)
                .map(Friend::getFriendUuid)
                .toList();

        return IntStream.range(0, friendRequestList.size())
                .mapToObj(i -> FriendRequestResDto.toDto(i, friendRequestList.get(i)))
                .toList();

    }

    //친구 수락
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

        FriendMessage friendMessage = FriendMessage.builder()
                .senderUuid(friendUuidReqDto.getFriendUuid())
                .receiverUuid(uuid)
                .content("친구 신청이 수락되었습니다.")
                .build();
        kafkaTemplate.send(TOPIC, friendMessage);

    }

    //친구 요청 거절
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

    //내가 보낸 친구 신청 목록 조회
    @Override
    public List<FriendSendResDto> getFriendSendList(String uuid) {

        List<Friend> friendList = friendRepository.findByUuid(uuid);

        List<String> friendSendList = friendList.stream()
                .filter(friend -> friend.getFriendType() == SENDER)
                .map(Friend::getFriendUuid)
                .toList();

        return IntStream.range(0, friendSendList.size())
                .mapToObj(i -> FriendSendResDto.toDto(i, friendSendList.get(i)))
                .toList();
    }
}







