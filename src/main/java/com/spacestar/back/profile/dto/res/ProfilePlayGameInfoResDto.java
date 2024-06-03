package com.spacestar.back.profile.dto.res;

import com.spacestar.back.profile.domain.PlayGame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProfilePlayGameInfoResDto {

    private int index;
    private Long gameId;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
    private String gameNickname;

    public static ProfilePlayGameInfoResDto toDto(PlayGame playGame, int index) {

        return ProfilePlayGameInfoResDto.builder()
                .index(index)
                .gameId(playGame.getGameId())
                .tierId(playGame.getTierId())
                .positionId(playGame.getPositionId())
                .classId(playGame.getClassId())
                .serverId(playGame.getServerId())
                .gameNickname(playGame.getGameNickname())
                .build();
    }
}
