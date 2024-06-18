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
import java.util.Collections;
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
     **/
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
        likedGameRepository.deleteAllByUuid(uuid);

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
        playGameRepository.deleteAllByUuid(uuid);

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

        return IntStream.range(0, playGameIds.size())
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

        profileRepository.updateSwipe(uuid, profileSwipeResDto.isSwipe());
    }


    /**
     * 프로필 사진
     **/
    //프로필 이미지 리스트 조회
    @Override
    public List<ProfileImageListResDto> findProfileImageList(String uuid) {

        List<ProfileImage> profileImages = profileImageRepository.findAllByUuid(uuid);

        //역순
        List<ProfileImageListResDto> dtoList = new ArrayList<>(IntStream.range(0, profileImages.size())
                .mapToObj(index -> ProfileImageListResDto.convertToDto(profileImages.size() - index - 1, profileImages.get(index)))
                .toList());
        Collections.reverse(dtoList);
        return dtoList;

    }

    //프로필 메인 이미지 조회
    @Override
    public ProfileMainImageResDto findMainProfileImage(String uuid) {

        ProfileImage profileImage = profileImageRepository.findByUuidAndMain(uuid, true);
        ProfileMainImageResDto profileMainImageResDto = ProfileMainImageResDto.builder()
                .profileImageUrl(null)
                .build();
        if (profileImage != null) {
            profileMainImageResDto = mapper.map(profileImage, ProfileMainImageResDto.class);
        }
        return profileMainImageResDto;
    }

    //프로필 사진 추가
    @Transactional
    @Override
    public void addProfileImage(String uuid, ProfileImageReqDto profileImageReqDto) {

        //기존 메인 이미지 가져오기
        ProfileImage mainImage = profileImageRepository.findByUuidAndMain(uuid, true);
        boolean isAddMain = profileImageReqDto.isMain();

        // 메인 이미지가 설정될 필요가 있을 때
        if (isAddMain && mainImage != null) {
            //기존 메인 이미지를 일반 이미지로 변경
            demoteMainImage(mainImage);
        } else if (mainImage == null) {
            isAddMain = true;
        }

        // 이미지 추가 또는 업데이트
        addOrUpdateProfileImage(uuid, profileImageReqDto, isAddMain);

    }


    //프로필 사진 삭제
    @Transactional
    @Override
    public void deleteProfileImage(String uuid, ProfileImageDeleteReqDto profileImageDeleteReqDto) {

        ProfileImage profileImage = profileImageRepository.findByUuidAndMain(uuid, true);

        //메인 사진 삭제
        if (profileImage.getProfileImageUrl().equals(profileImageDeleteReqDto.getProfileImageUrl())) {

            profileImageRepository.delete(profileImage);
            //이전 이미지가 메인이 됨
            ProfileImage newMain = profileImageRepository.findLastByUuid(uuid);

            if (newMain != null) {
                profileImageRepository.save(ProfileImageReqDto.updateImage(uuid, newMain.getId(), true, newMain.getProfileImageUrl()));
            }


        }
        //일반 사진 삭제
        else {

            ProfileImage beDelete = profileImageRepository.findByUuidAndProfileImageUrl(uuid, profileImageDeleteReqDto.getProfileImageUrl());
            profileImageRepository.delete(beDelete);
        }

    }

    //로그인 시 프로필 존재 유무판단
    @Transactional
    @Override
    public Void existProfile(String uuid) {

        //기본 프로필 생성
        profileRepository.save(Profile.builder()
                .uuid(uuid)
                .exp(0L)
                .reportCount(0)
                .swipe(true)
                .build());


    }

    // 프로필 이미지 메서드
    private void addOrUpdateProfileImage(String uuid, ProfileImageReqDto profileImageReqDto, boolean isAddMain) {

        ProfileImage existImage = profileImageRepository.findByUuidAndProfileImageUrl(uuid, profileImageReqDto.getProfileImageUrl());

        if (existImage == null) {
            //새 이미지 추가
            profileImageRepository.save(ProfileImageReqDto.updateImage(uuid, null, isAddMain, profileImageReqDto.getProfileImageUrl()));
        } else {
            // 이미 존재하는 이미지를 업데이트
            profileImageRepository.save(ProfileImageReqDto.updateImage(uuid, existImage.getId(), isAddMain, existImage.getProfileImageUrl()));
        }
    }

    private void demoteMainImage(ProfileImage mainImage) {

        //기존 메인 이미지의 메인 속성을 false로 업데이트
        profileImageRepository.save(ProfileImageReqDto.updateImage(
                mainImage.getUuid(),
                mainImage.getId(),
                false,
                mainImage.getProfileImageUrl()));

    }

    /***
     * 퀵 프로필 조회
     */

    @Override
    public QuickMemberInfoResDto quickMemberInfo(String uuid) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        PlayGame playGame = playGameRepository.findByUuidAndMain(uuid, true);

        return QuickMemberInfoResDto.converter(profile.getGamePreferenceId(), profile.getMbtiId(), playGame.getGameId(), profile.getReportCount());
    }

}
