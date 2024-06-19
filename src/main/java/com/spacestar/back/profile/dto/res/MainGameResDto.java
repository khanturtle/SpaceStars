package com.spacestar.back.profile.dto.res;

import com.spacestar.back.profile.domain.PlayGame;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainGameResDto {

    private Long gameId;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
    private String gameNickname;
    private boolean main;

    public static MainGameResDto toDto(PlayGame playGame) {

        return MainGameResDto.builder()
                .gameId(playGame.getGameId())
                .tierId(playGame.getTierId())
                .positionId(playGame.getPositionId())
                .classId(playGame.getClassId())
                .serverId(playGame.getServerId())
                .gameNickname(playGame.getGameNickname())
                .main(playGame.isMain())
                .build();
    }
}
