package com.spacestar.back.member.domain;

import com.spacestar.back.member.dto.req.MemberInfoGameReqDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PlayGame {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameId;

    private Boolean main;

    private Long tierId;

    private Long positionId;

    private Long classId;

    private Long serverId;

    private String uuid;

    private String gameNickname;

    @Builder
    public PlayGame(Long id, Long gameId, Boolean main, Long tierId, Long positionId, Long classId, Long serverId, String uuid, String gameNickname) {
        this.id = id;
        this.gameId = gameId;
        this.main = main;
        this.tierId = tierId;
        this.positionId = positionId;
        this.classId = classId;
        this.serverId = serverId;
        this.uuid = uuid;
        this.gameNickname = gameNickname;
    }

    public static PlayGame updateMainToEntity(String uuid, MemberInfoGameReqDto memberInfoGameReqDto) {
        return PlayGame.builder()
                .gameId(memberInfoGameReqDto.getGameId())
                .main(true)
                .gameNickname(memberInfoGameReqDto.getGameNickname())
                .tierId(memberInfoGameReqDto.getTierId())
                .positionId(memberInfoGameReqDto.getPositionId())
                .classId(memberInfoGameReqDto.getClassId())
                .serverId(memberInfoGameReqDto.getServerId())
                .uuid(uuid)
                .build();
    }

    public static PlayGame updateGameToEntity(String uuid, MemberInfoGameReqDto memberInfoGameReqDto){
        return PlayGame.builder()
                .gameId(memberInfoGameReqDto.getGameId())
                .main(false)
                .gameNickname(memberInfoGameReqDto.getGameNickname())
                .tierId(memberInfoGameReqDto.getTierId())
                .positionId(memberInfoGameReqDto.getPositionId())
                .classId(memberInfoGameReqDto.getClassId())
                .serverId(memberInfoGameReqDto.getServerId())
                .uuid(uuid)
                .build();
    }
}
