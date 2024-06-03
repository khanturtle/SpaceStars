package com.spacestar.back.profile.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PlayGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long gameId;

    private Long tierId;

    private Long positionId;

    private Long classId;

    private Long serverId;

    private boolean main;

    @NotNull
    @Column(length = 100)
    private String uuid;

    @NotNull
    @Column(length = 50)
    private String gameNickname;

    @Builder

    public PlayGame(Long id, Long gameId, Long tierId, Long positionId, Long classId, Long serverId, boolean main, String uuid, String gameNickname) {
        this.id = id;
        this.gameId = gameId;
        this.tierId = tierId;
        this.positionId = positionId;
        this.classId = classId;
        this.serverId = serverId;
        this.main = main;
        this.uuid = uuid;
        this.gameNickname = gameNickname;
    }
}
