package com.spacestar.back.profile.service;

import com.spacestar.back.global.GlobalException;
import com.spacestar.back.profile.domain.LikedGame;
import com.spacestar.back.profile.domain.PlayGame;
import com.spacestar.back.profile.domain.Profile;
import com.spacestar.back.profile.dto.req.ProfileInfoReqDto;
import com.spacestar.back.profile.dto.req.ProfilePlayGameInfoReqDto;
import com.spacestar.back.profile.repository.LikedGameRepository;
import com.spacestar.back.profile.repository.PlayGameRepository;
import com.spacestar.back.profile.repository.ProfileRepository;
import com.spacestar.back.profile.vo.req.ProfileInfoReqVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spacestar.back.global.ResponseStatus;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;
    private final LikedGameRepository likedGameRepository;
    private final PlayGameRepository playGameRepository;

    @Transactional
    @Override
    public void updateProfileInfo(String uuid, ProfileInfoReqDto profileInfoReqDto) {

        Profile profile = profileRepository.findByUuid(uuid)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NOT_EXIST_PROFILE));

        profileRepository.save(profileInfoReqDto.updateToEntity(profile.getId(), profile.getUuid(), profileInfoReqDto));

        //내가 좋아하는 게임
        if (profileInfoReqDto.getLikedGameIds().isEmpty()) {
            likedGameRepository.deleteAllByUuid(uuid);
        }
        else{
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
        if (profileInfoReqDto.getPlayGameIds().isEmpty()){
            playGameRepository.deleteAllByUuid(uuid);
        }
        else{
            //플레이한 게임 삭제
            playGameRepository.deleteAllByUuid(uuid);

            //플레이한 게임 추가
            for (ProfilePlayGameInfoReqDto profilePlayGameInfoReqDto : profileInfoReqDto.getPlayGameIds()){
                //메인 게임 수정
                if (profileInfoReqDto.getMainGameId().equals(profilePlayGameInfoReqDto.getGameId())) {
                    playGameRepository.save(profilePlayGameInfoReqDto.toEntity(uuid, true,profilePlayGameInfoReqDto));
                }
                else{
                    playGameRepository.save(profilePlayGameInfoReqDto.toEntity(uuid, false,profilePlayGameInfoReqDto));
                }
            }
        }
    }
}
