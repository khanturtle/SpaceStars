package com.spacestar.back.profile.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.global.ResponseStatus;
import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.domain.ProfileImage;
import com.spacestar.back.profile.dto.req.*;
import com.spacestar.back.profile.dto.res.*;
import com.spacestar.back.profile.repository.ProfileImageRepository;
import com.spacestar.back.profile.repository.ProfileRepository;
import com.spacestar.back.profile.vo.req.LikedGameInfoReqVo;
import com.thoughtworks.xstream.XStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spacestar.back.profile.domain.LikedGame;
import com.spacestar.back.profile.domain.PlayGame;
import com.spacestar.back.profile.repository.LikedGameRepository;
import com.spacestar.back.profile.repository.PlayGameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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

    /**
     * 프로필 정보 (프로필, 좋아하는게임, 내가 하는게임)
     * **/
    //프로필 정보 수정
    @Transactional
    @Override
    public void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        profileRepository.save(ProfileInfoReqDto.updateProfileInfo(profile, profileInfoReqDto));
    }

    //좋아하는 게임 정보 수정
    @Transactional
    @Override
    public void updateLikedGameInfo(String uuid, LikedGameInfoReqDto likedGameInfoReqDto) {

        List<LikedGame> likedGameList = likedGameRepository.findAllByUuid(uuid);

        //기존 좋아하는 게임 삭제
        likedGameRepository.deleteAll(likedGameList);

        //새로운 좋아하는 게임 추가
        List<LikedGame> likedGames = likedGameInfoReqDto.getLikedGameIdList().stream()
                .map(gameId -> LikedGame.builder()
                        .uuid(uuid)
                        .gameId(gameId)
                        .build())
                .toList();

        likedGameRepository.saveAll(likedGames);
    }

    //내가 하는 게임 정보 수정
    @Transactional
    @Override
    public void updatePlayGameInfo(String uuid, List<PlayGameInfoReqDto> playGameInfoReqDtos) {

        List<PlayGame> playGameList = playGameRepository.findAllByUuid(uuid);

        //기존 내가 하는 게임 삭제
        playGameRepository.deleteAll(playGameList);

        //새로운 내가 하는 게임 추가
        List<PlayGame> playGames = playGameInfoReqDtos.stream()
                .map(playGameInfoReqDto -> PlayGameInfoReqDto.toEntity(uuid, playGameInfoReqDto))
                .toList();

        playGameRepository.saveAll(playGames);
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

        List<Long> likedGameIdList = likedGameIds.stream()
                .map(LikedGame::getGameId)
                .toList();

        return new ProfileLikedGameResDto(likedGameIdList);
    }

    // 내가 하는 게임 조회
    @Override
    public List<ProfilePlayGameInfoResDto> getPlayGame(String uuid) {

        List<PlayGame> playGameIds = playGameRepository.findAllByUuid(uuid);

        return IntStream.range(0,playGameIds.size())
                .mapToObj(index -> ProfilePlayGameInfoResDto.toDto(playGameIds.get(index), index))
                .toList();
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


    /**
     * 프로필 사진
     * **/
    //프로필 이미지 리스트 조회
    @Override
    public List<ProfileImageListResDto> findProfileImageList(String uuid) {

        List<ProfileImage> profileImages = profileImageRepository.findAllByUuid(uuid);

        return IntStream.range(0, profileImages.size())
                .mapToObj(index -> ProfileImageListResDto.convertToDto(index, profileImages.get(index)))
                .toList();
    }

    //프로필 메인 이미지 조회
    @Override
    public ProfileMainImageResDto findMainProfileImage(String uuid) {

        return mapper.map(
                profileImageRepository.findByUuidAndMain(uuid, true), ProfileMainImageResDto.class);
    }

    //프로필 사진 추가
    @Transactional
    @Override
    public void addProfileImage(String uuid, ProfileImageReqDto profileImageReqDto) {

        //메인 프로필이 없으면 메인으로
        if (!profileImageRepository.existsByUuidAndMain(uuid, true)){
            profileImageRepository.save(ProfileImageReqDto.addNewImage(uuid, true,profileImageReqDto));
        }
        else{
            profileImageRepository.save(ProfileImageReqDto.addNewImage(uuid, false,profileImageReqDto));
        }

    }

    //프로필 사진 삭제
    @Transactional
    @Override
    public void deleteProfileImage(String uuid, ProfileImageReqDto profileImageReqDto) {

        //메인 프로필이면 삭제 안되게
        if (profileImageRepository.existsByUuidAndMain(uuid, true)){
            throw new GlobalException(ResponseStatus.MAIN_PROFILE_IMAGE_DELETE);
        }
        else{
            profileImageRepository.delete(profileImageRepository.findByUuidAndProfileImageUrl(uuid, profileImageReqDto.getProfileImageUrl()));
        }

    }

    // 메인 프로필 사진 설정
    @Transactional
    @Override
    public void mainProfileImage(String uuid, ProfileImageReqDto profileImageReqDto) {

        ProfileImage profileImage = profileImageRepository.findByUuidAndMain(uuid, true);

        if ( profileImage != null){
            profileImageRepository.save(ProfileImageReqDto.updateImage(uuid, false, profileImage.getId(), profileImageReqDto));
        }

        profileImageRepository.save(ProfileImageReqDto.addNewImage(uuid, true, profileImageReqDto));

    }

    //로그인 시 프로필 존재 유무판단
    @Transactional
    @Override
    public ProfileExistResDto existProfile(String uuid) {

        Optional<Profile> profile = profileRepository.findByUuid(uuid);
        List<LikedGame> likedGame = likedGameRepository.findAllByUuid(uuid);
        List<PlayGame> playGame = playGameRepository.findAllByUuid(uuid);

        if (profile.isEmpty()){

            //기본 프로필 생성
            profileRepository.save(Profile.builder()
                    .uuid(uuid)
                    .exp(0L)
                    .reportCount(0)
                    .swipe(true)
                    .build());

            return ProfileExistResDto.builder()
                    .isExist(false)
                    .build();
        }

        //좋아하는 게임, 플레이한 게임이 없을 경우
        if (likedGame.isEmpty() || playGame.isEmpty()) {
            return ProfileExistResDto.builder()
                    .isExist(false)
                    .build();
        }

        return ProfileExistResDto.builder()
                .isExist(true)
                .build();
    }
}
