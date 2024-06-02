package com.spacestar.back.profile.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

}
