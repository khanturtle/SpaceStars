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
public class PlayGameInfoReqDto {

    private Long gameId;
    private Long tierId;
    private Long positionId;
    private Long classId;
    private Long serverId;
    private String gameNickname;
    private boolean main;

    public static PlayGame toEntity(String uuid,PlayGameInfoReqDto dto){
        return PlayGame.builder()
                .uuid(uuid)
                .gameId(dto.getGameId())
                .tierId(dto.getTierId())
                .positionId(dto.getPositionId())
                .classId(dto.getClassId())
                .serverId(dto.getServerId())
                .gameNickname(dto.getGameNickname())
                .main(dto.isMain())
                .build();
    }

}
