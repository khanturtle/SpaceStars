package com.spacestar.back.profile.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import com.spacestar.back.profile.dto.req.KakaoProfileImageReqDto;
import com.spacestar.back.profile.dto.req.ProfileImageReqDto;
import com.spacestar.back.profile.dto.res.*;
import com.spacestar.back.profile.repository.ProfileImageRepository;
import com.spacestar.back.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spacestar.back.profile.domain.LikedGame;
import com.spacestar.back.profile.domain.PlayGame;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.req.ProfilePlayGameInfoReqDto;
import com.spacestar.back.profile.repository.LikedGameRepository;
import com.spacestar.back.profile.repository.PlayGameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;
    private final LikedGameRepository likedGameRepository;
    private final PlayGameRepository playGameRepository;
    private final ProfileImageRepository profileImageRepository;
    private final ModelMapper mapper;

    //프로필 정보 수정
    @Transactional
    @Override
    public void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        //프로필 정보 수정
        profileRepository.save(profileInfoReqDto.updateToEntity(profile.getId(), profile.getUuid(), profileInfoReqDto));

        //내가 좋아하는 게임
        if (profileInfoReqDto.getLikedGameIds().isEmpty()) {
            likedGameRepository.deleteAllByUuid(uuid);
        } else {
            //좋아하는 게임 삭제
            likedGameRepository.deleteAllByUuid(uuid);

            //좋아하는 게임 추가
            for (Long ids : profileInfoReqDto.getLikedGameIds()) {

                LikedGame likeGame = LikedGame.builder()
                        .gameId(ids)
                        .uuid(uuid)
                        .build();

                likedGameRepository.save(likeGame);
            }
        }

        //내가 플레이한 게임
        if (profileInfoReqDto.getPlayGameIds().isEmpty()) {
            playGameRepository.deleteAllByUuid(uuid);
        } else {
            //플레이한 게임 삭제
            playGameRepository.deleteAllByUuid(uuid);

            //플레이한 게임 추가
            for (ProfilePlayGameInfoReqDto profilePlayGameInfoReqDto : profileInfoReqDto.getPlayGameIds()) {
                //메인 게임 수정
                if (profileInfoReqDto.getMainGameId().equals(profilePlayGameInfoReqDto.getGameId())) {
                    playGameRepository.save(profilePlayGameInfoReqDto.toEntity(uuid, true, profilePlayGameInfoReqDto));
                } else {
                    playGameRepository.save(profilePlayGameInfoReqDto.toEntity(uuid, false, profilePlayGameInfoReqDto));
                }
            }
        }
    }

    //프로필 정보 조회
    @Override
    public ProfileInfoResDto getProfileInfo(String uuid) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        return ProfileInfoResDto.toDto(profile);
    }

    //좋아하는 게임 조회
    @Override
    public ProfileLikedGameResDto getLikedGame(String uuid) {

        List<LikedGame> likedGameIds = likedGameRepository.findAllByUuid(uuid);
        List<Long> likedGameIdList = new ArrayList<>();

        for (LikedGame likedGame : likedGameIds) {
            likedGameIdList.add(likedGame.getGameId());
        }

        return ProfileLikedGameResDto.builder()
                .likedGameIdList(likedGameIdList)
                .build();
    }

    // 내가 하는 게임 조회
    @Override
    public List<ProfilePlayGameInfoResDto> getPlayGame(String uuid) {

        List<PlayGame> playGameIds = playGameRepository.findAllByUuid(uuid);
        List<ProfilePlayGameInfoResDto> profilePlayGameInfoResDtoList = new ArrayList<>();

        int index = 0;
        for (PlayGame playGame : playGameIds) {
            profilePlayGameInfoResDtoList.add(ProfilePlayGameInfoResDto.toDto(playGame, index));
            index++;
        }
        return profilePlayGameInfoResDtoList;

    }

    //프로필 이미지 수정
    @Transactional
    @Override
    public void updateProfileImages(String uuid, List<ProfileImageReqDto> profileImageReqDtos) {

        List<ProfileImage> profileImages = profileImageRepository.findAllByUuid(uuid);

        //사진 삭제
        for (ProfileImage profileImage : profileImages) {
            boolean check = false;
            for (ProfileImageReqDto profileImageReqDto : profileImageReqDtos) {
                if (profileImage.getProfileImageUrl().equals(profileImageReqDto.getProfileImageUrl())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                profileImageRepository.delete(profileImage);
            }
        }

        // 사진 저장
        for (ProfileImageReqDto profileImageReqDto : profileImageReqDtos) {
            boolean check = false;
            for (ProfileImage profileImage : profileImages) {
                //사진 존재
                if (profileImageReqDto.getProfileImageUrl().equals(profileImage.getProfileImageUrl())) {
                    profileImageRepository.save(profileImageReqDto.updateImage(uuid,profileImage, profileImageReqDto));
                    check = true;
                }
            }
            //사진 존재하지 않음
            if (!check) {
                profileImageRepository.save(profileImageReqDto.addNewImage(uuid, profileImageReqDto));

            }
        }
    }

    //프로필 이미지 리스트 조회
    @Override
    public List<ProfileImageListResDto> findProfileImageList(String uuid) {

        List<ProfileImageListResDto> profileImageListResDtos = new ArrayList<>();

        int i = 0;
        for (ProfileImage profileImage : profileImageRepository.findAllByUuid(uuid)) {
            profileImageListResDtos.add(ProfileImageListResDto.convertToDto(i, profileImage));
            i++;
        }
        return profileImageListResDtos;
    }

    //프로필 메인 이미지 조회
    @Override
    public ProfileMainImageResDto findMainProfileImage(String uuid) {

        return mapper.map(
                profileImageRepository.findByUuidAndMain(uuid, true), ProfileMainImageResDto.class);
    }

    // 회원가입 시 카카오 프로필 사진 저장
    @Transactional
    @Override
    public void addProfileImage(String uuid, KakaoProfileImageReqDto kakaoProfileImageReqDto) {

        profileImageRepository.save(kakaoProfileImageReqDto.addNewImage(uuid, kakaoProfileImageReqDto));
    }

    //로그인 시 프로필 존재 유무판단
    @Transactional
    @Override
    public Boolean existProfile(String uuid) {

        Optional<Profile> profile = profileRepository.findByUuid(uuid);
        Optional<LikedGame> likedGame = likedGameRepository.findByUuid(uuid);
        Optional<PlayGame> playGame = playGameRepository.findByUuid(uuid);

        if (profile.isEmpty()){

            //기본 프로필 생성
            profileRepository.save(Profile.builder()
                    .uuid(uuid)
                    .exp(0L)
                    .reportCount(0)
                    .swipe(true)
                    .build());

            return false;
        }

        //좋아하는 게임, 플레이한 게임이 없을 경우
        if (likedGame.isEmpty() || playGame.isEmpty()) {
            return false;
        }

        return true;
    }

    //스와이프 추천 여부 조회
    @Override
    public ProfileSwipeResDto findSwipeRecommend(String uuid) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        return ProfileSwipeResDto.builder()
                .swipe(profile.isSwipe())
                .build();
    }

    //스와이프 추천 여부 변경
    @Transactional
    @Override
    public void updateSwipeRecommend(String uuid, ProfileSwipeResDto profileSwipeResDto) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        profileRepository.updateSwipe(uuid,profileSwipeResDto.isSwipe());
    }

}
