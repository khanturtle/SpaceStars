package com.spacestar.back.profile.dto.req;

import com.spacestar.back.profile.domain.PlayGame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfilePlayGameInfoReqDto {

    private Long gameId;
    private String gameNickname;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;

    public PlayGame toEntity(String uuid, boolean main, ProfilePlayGameInfoReqDto profilePlayGameInfoReqDto) {
        return PlayGame.builder()
                .gameId(profilePlayGameInfoReqDto.getGameId())
                .uuid(uuid)
                .main(main)
                .gameNickname(profilePlayGameInfoReqDto.getGameNickname())
                .tierId(profilePlayGameInfoReqDto.getTierId())
                .positionId(profilePlayGameInfoReqDto.getPositionId())
                .classId(profilePlayGameInfoReqDto.getClassId())
                .serverId(profilePlayGameInfoReqDto.getServerId())
                .build();
    }

}
